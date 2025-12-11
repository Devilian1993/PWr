#include "Tree.h"
#include "Node/Node.h"
#include <iostream>
#include <map>

Tree::Tree() : root(nullptr), printTreeAfterEnter(false) {}

Tree::~Tree() {
    clear();
}

void Tree::clear() {
    delete root;
    root = nullptr;
}

Tree::Tree(const Tree& other) : root(nullptr), printTreeAfterEnter(false) {
    if (other.root) {
        root = cloneNode(other.root);
    }
}

Tree& Tree::operator=(const Tree& other) {
    if (this != &other) {
        clear();
        if (other.root) {
            root = cloneNode(other.root);
        }
    }
    return *this;
}


Result<void, Error> Tree::enter(const std::vector<std::string> &tokens) {
    int offset = 0;
    Result<Node*, Error> buildResult = buildTree(tokens, offset);

    if (!buildResult.isSuccess()) {
        return Result<void, Error>::fail(buildResult.getErrors());
    }

    Node* newRootCandidate = buildResult.getValue();

    if (offset < tokens.size()) {
        delete newRootCandidate;
        return Result<void, Error>::fail(new Error("Too many arguments provided!"));
    }

    clear();
    root = newRootCandidate;

    return Result<void, Error>::ok();
}

Result<Node *, Error> Tree::buildTree(const std::vector<std::string> &tokens, int &offset) {
    if (offset >= tokens.size()) {
        return Result<Node*, Error>::fail(new Error("Missing arguments! Expression is incomplete."));
    }

    std::string token = tokens[offset++];
    Node* newNode = createNodeByToken(token);


    VariableNode* varNode = dynamic_cast<VariableNode*>(newNode);
    if (varNode && varNode->toString() == "error") {
        delete newNode;
        return Result<Node*, Error>::fail(new Error("Invalid token found: " + token));
    }

    int expectedArgs = newNode->numberOfArguments();
    for (int i = 0; i < expectedArgs; ++i) {
        Result<Node*, Error> childResult = buildTree(tokens, offset);

        if (!childResult.isSuccess()) {
            delete newNode;
            return Result<Node*, Error>::fail(childResult.getErrors());
        }

        newNode->addChild(childResult.getValue());
    }

    return Result<Node*, Error>::ok(newNode);
}

Node* Tree::createNodeByToken(const std::string& token) const {
    if (token == "+") return new AdditionNode();
    if (token == "-") return new SubtractionNode();
    if (token == "*") return new MultiplicationNode();
    if (token == "/") return new DivisionNode();
    if (token == "sin") return new SinNode();
    if (token == "cos") return new CosNode();

    char* endPtr;
    double val = std::strtod(token.c_str(), &endPtr);
    if (endPtr != token.c_str()) {
        return new ConstantNode(val);
    }

    std::string validName;
    for(int i=0; i<token.length(); ++i) {
         if((token[i] >= 'a' && token[i] <= 'z') ||
            (token[i] >= 'A' && token[i] <= 'Z') ||
            (token[i] >= '0' && token[i] <= '9')) {
             validName += token[i];
         }
    }
    if (validName.empty()) return new VariableNode("error");

    return new VariableNode(validName);
}

Node*& Tree::findLeafReference(Node*& currentNode) {
    if (currentNode->isLeaf()) {
        return currentNode;
    }
    return findLeafReference(currentNode->getChildren()[0]);
}

Node* Tree::cloneNode(Node* node) const {
    if (!node) return nullptr;

    Node* newNode = node->clone();
    const std::vector<Node*>& children = node->getChildren();

    for (Node* i : children) {
        newNode->addChild(cloneNode(i));
    }
    return newNode;
}

Tree Tree::operator+(const Tree& other) const {
    Tree result;

    if (!this->root) {
        result = other;
        return result;
    }

    result = *this;

    if (!other.root) return result;

    Node*& leafRef = result.findLeafReference(result.root);
    delete leafRef;
    leafRef = cloneNode(other.root);

    return result;
}

std::string Tree::toString() const {
    std::string text;
    if (root) getNodeText(root, text);
    return text;
}

void Tree::getNodeText(Node* node, std::string& text) const {
    if (!node) return;
    text += node->toString() + " ";
    const std::vector<Node*>& children = node->getChildren();
    for (Node* i : children) {
        getNodeText(i, text);
    }
}

std::vector<std::string> Tree::getVars() const {
    std::set<std::string> varsSet;
    if (root) {
        collectVars(root, varsSet);
    }
    std::vector result(varsSet.begin(), varsSet.end());
    return result;
}

void Tree::collectVars(Node* node, std::set<std::string>& vars) const {
    if (!node) return;
    if (dynamic_cast<VariableNode*>(node)) {
        vars.insert(node->toString());
    }
    const std::vector<Node*>& children = node->getChildren();
    for (Node* i : children) {
        collectVars(i, vars);
    }
}

double Tree::comp(const std::vector<double>& values) const {
    if (!root) return 0.0;

    std::vector<std::string> vars = getVars();
    if (values.size() != vars.size()) {
        std::cout << "Blad: Zla ilość zmiennych." << std::endl;
        return 0.0;
    }

    std::map<std::string, double> varMap;
    for (int i = 0; i < vars.size(); ++i) {
        varMap[vars[i]] = values[i];
    }

    return root->evaluate(varMap);
}