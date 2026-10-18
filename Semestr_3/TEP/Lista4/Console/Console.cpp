//
// Created by Maks on 26.11.2025.
//

#include "Console.h"
#include "../Tree/Tree.h"
#include "../Utils/Utils.h"
#include "../Result/Result.h"
#include "../ResultSaver/ResultSaver.h"
#include <iostream>

void Console::enter(const std::vector<std::string>& input) {
    if (input.size() < 2) {
        std::cout << "Blad: Nie podano formuly." << std::endl;
        return;
    }

    std::vector formulaTokens(input.begin() + 1, input.end());

    Result<void, Error> result = tree.enter(formulaTokens);

    if (result.isSuccess()) {
        std::cout << "Wczytano nowe wyrazenie (Sukces)." << std::endl;
        Result<Tree*, Error> saveResult = Result<Tree*, Error>::ok(&tree);
        ResultSaver<Tree*, Error>::saveToFile(saveResult, "tree.txt");
    } else {
        std::cout << "Blad podczas tworzenia drzewa (Drzewo nie zostalo zmienione):" << std::endl;
        for (const Error* err : result.getErrors()) {
            Result<Tree*, Error> errorResult = Result<Tree*, Error>::fail(result.getErrors());
            ResultSaver<Tree*, Error>::saveToFile(errorResult, "tree.txt");
            std::cout << " -> " << err->getMessage() << std::endl;
        }
    }
}

void Console::vars() const {
    std::vector<std::string> variables = tree.getVars();
    std::cout << "Zmienne w drzewie: ";
    for (const std::string& variable : variables) {
        std::cout << variable << " ";
    }
    std::cout << std::endl;
}

void Console::print() const {
    std::cout << "Aktualne wyrazenie: ";
    std::cout << tree.toString() << std::endl;
}

void Console::comp(const std::vector<std::string> &input) const {
    std::vector<double> values;

    for (int i = 1; i < input.size(); ++i) {
        values.push_back(std::strtod(input[i].c_str(), nullptr));
    }

    double result = tree.comp(values);
    std::cout << "Wynik: " << result << std::endl;
}

void Console::join(const std::vector<std::string>& input) {
    if (input.size() < 2) {
        std::cout << "Blad: Nie podano formuly do polaczenia." << std::endl;
        return;
    }

    std::vector formulaTokens(input.begin() + 1, input.end());

    Tree otherTree;
    otherTree.enter(formulaTokens);

    tree = tree + otherTree;

    std::cout << "Drzewa zostaly polaczone." << std::endl;
}

void Console::exit() {
    running = false;
}

void Console::readCommand() {
    std::cout << "> ";
    std::string input;
    std::getline(std::cin, input);
    matchCommand(input);
}

void Console::matchCommand(const std::string& input) {
    if (input.empty()) return;

    const std::vector<std::string> commandVector = Utils::split(input);

    if (commandVector.empty()) return;

    const std::string& command = commandVector[0];

    if (command == "enter") {
        enter(commandVector);
    } else if (command == "vars") {
        vars();
    } else if (command == "print") {
        print();
    } else if (command == "comp") {
        comp(commandVector);
    } else if (command == "join") {
        join(commandVector);
    } else if (command == "exit") {
        exit();
    } else {
        std::cout << "Nieznana komenda: " << command << std::endl;
    }
}

void Console::start() {
    running = true;
    std::cout << "##### Program aktywny #####" << std::endl;
    std::cout << "Dostepne komendy: enter, vars, print, comp, join, exit" << std::endl;

    while (running) {
        readCommand();
    }

    std::cout << "##### Program wylaczony #####" << std::endl;
}

Console::Console() = default;
