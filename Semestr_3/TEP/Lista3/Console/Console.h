#include <string>
#include <vector>
#include <map>

#include "../Tree/Tree.h"

#ifndef LISTA3TEP_CONSOLE_H
#define LISTA3TEP_CONSOLE_H


class Console {
private:
    Tree tree;
    bool running = true;
    void enter(const std::vector<std::string>& input);;
    void vars() const;
    void print() const;
    void comp(const std::vector<std::string>& input) const;
    void join(const std::vector<std::string>& input);
    void readCommand();
    void exit();
    void matchCommand(const std::string& input);
public:
    Console();
    void start();
};


#endif //LISTA3TEP_CONSOLE_H