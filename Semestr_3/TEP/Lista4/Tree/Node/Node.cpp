#include "Node.h"

#include <cmath>
#include <iostream>
#include <ostream>

Node::~Node() {
    for (Node* & i : children) {
        delete i;
    }
    children.clear();
}

bool Node::isLeaf() const {
    return children.empty();
}

void Node::addChild(Node *child) {
    children.push_back(child);
}

std::vector<Node *> & Node::getChildren() {
    return children;
}

//Stała

ConstantNode::ConstantNode(double val) {
    value = val;
}

int ConstantNode::numberOfArguments() const {
    return 0;
}

double ConstantNode::evaluate(const std::map<std::string, double> &vars) const {
    return value;
}

std::string ConstantNode::toString() const {
    return std::to_string(value);
}


//Zmienna

VariableNode::VariableNode(std::string n) {
    name = n;
}

int VariableNode::numberOfArguments() const {
    return 0;
}

double VariableNode::evaluate(const std::map<std::string, double>& vars) const {
    if (vars.contains(name)) {
        return vars.at(name);
    }
    return 0.0;
}

std::string VariableNode::toString() const {
    return name;
}

// Dodawanie

AdditionNode::AdditionNode() = default;
int AdditionNode::numberOfArguments() const { return 2; }
std::string AdditionNode::toString() const { return "+"; }
double AdditionNode::evaluate(const std::map<std::string, double>& vars) const {
    return children[0]->evaluate(vars) + children[1]->evaluate(vars);
}

//Odejmowanie

SubtractionNode::SubtractionNode() = default;
int SubtractionNode::numberOfArguments() const { return 2; }
std::string SubtractionNode::toString() const { return "-"; }
double SubtractionNode::evaluate(const std::map<std::string, double>& vars) const {
    return children[0]->evaluate(vars) - children[1]->evaluate(vars);
}

//Mnożenie

MultiplicationNode::MultiplicationNode() = default;
int MultiplicationNode::numberOfArguments() const { return 2; }
std::string MultiplicationNode::toString() const { return "*"; }
double MultiplicationNode::evaluate(const std::map<std::string, double>& vars) const {
    return children[0]->evaluate(vars) * children[1]->evaluate(vars);
}

//Dzielenie

DivisionNode::DivisionNode() = default;
int DivisionNode::numberOfArguments() const { return 2; }
std::string DivisionNode::toString() const { return "/"; }
double DivisionNode::evaluate(const std::map<std::string, double>& vars) const {
    double val2 = children[1]->evaluate(vars);
    if (val2 == 0) {
        std::cout << "Blad! Dzielenie przez 0. Wynik = 0.0." << std::endl;
        return 0.0;
    }
    return children[0]->evaluate(vars) / val2;
}

//Sinus

SinNode::SinNode() = default;
int SinNode::numberOfArguments() const { return 1; }
std::string SinNode::toString() const { return "sin"; }
double SinNode::evaluate(const std::map<std::string, double>& vars) const {
    return std::sin(children[0]->evaluate(vars));
}

//Cosinus

CosNode::CosNode() = default;
int CosNode::numberOfArguments() const { return 1; }
std::string CosNode::toString() const { return "cos"; }
double CosNode::evaluate(const std::map<std::string, double>& vars) const {
    return std::cos(children[0]->evaluate(vars));
}

Node* ConstantNode::clone() const {
    return new ConstantNode(value);
}

Node* VariableNode::clone() const {
    return new VariableNode(name);
}

Node* AdditionNode::clone() const {
    return new AdditionNode();
}

Node* SubtractionNode::clone() const {
    return new SubtractionNode();
}

Node* MultiplicationNode::clone() const {
    return new MultiplicationNode();
}

Node* DivisionNode::clone() const {
    return new DivisionNode();
}

Node* SinNode::clone() const {
    return new SinNode();
}

Node* CosNode::clone() const {
    return new CosNode();
}







