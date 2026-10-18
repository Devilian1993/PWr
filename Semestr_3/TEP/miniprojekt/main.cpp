#include <iostream>
#include <string>
#include "Evaluator.h"
#include "GeneticAlgorithm.h"


template <typename T>
T getInput(const std::string& prompt, T minVal, T maxVal) {
    T value;
    while (true) {
        std::cout << prompt;
        if (std::cin >> value && value >= minVal && value <= maxVal) {
            //std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
            return value;
        }
        std::cout << "Bledna wartosc! Podaj liczbe z zakresu [" << minVal << " - " << maxVal << "].\n";
        std::cin.clear();
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    }
}

int main() {
    std::string instancePath;
    std::cout << "Podaj sciezke do pliku z danymi (.lcvrp): ";
    std::getline(std::cin, instancePath);

    Evaluator evaluator;
    std::cout << "Wczytywanie " << instancePath << std::endl;

    evaluator.load(instancePath);
    std::cout << "Pomyslnie wczytano dane." << std::endl;

    int numberOfGroups = getInput<int>(
            "Liczba ciezarowek: ",
            1, 50000
        );

    int populationSize = getInput<int>(
        "Rozmiar populacji: ",
        10, 100000
    );

    int maxIterations = getInput<int>(
        "Liczba iteracji: ",
        1, 10000000
    );

    double mutationProb = getInput<double>(
        "Prawdopodobienstwo mutacji: ",
        0.0, 1.0
    );

    double crossoverProb = getInput<double>(
        "Prawdopodobienstwo krzyzowania: ",
        0.0, 1.0
    );

    GeneticAlgorithm algorithm(populationSize, mutationProb, crossoverProb,
                               maxIterations, numberOfGroups, evaluator);

    std::cout << "START" << std::endl;

    algorithm.run();


    Individual best = algorithm.getBestSolution();

    std::cout << "KONIEC" << std::endl;

    std::cout << "Najlepszy znaleziony koszt: " << best.getFitness() << std::endl;

    std::cout << "Genotyp: ";
    const std::vector<int>& genes = best.getGenotype();
    for (int g : genes) {
        std::cout << g << " ";
    }

    std::cout << std::endl;


    return 0;
}