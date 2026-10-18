#ifndef TREE_H
#define TREE_H

#include "Node/Node.h"
#include <vector>
#include <string>
#include <set>

class Tree {
    friend class Console;
private:
    Node* root;
    bool printTreeAfterEnter;
    void clear();
    Node* buildTree(const std::vector<std::string>& tokens, int& offset);
    Node* createNodeByToken(const std::string& token) const;
    Node* cloneNode(Node* node) const;
    Node*& findLeafReference(Node*& currentNode);
    void collectVars(Node* node, std::set<std::string>& vars) const;
    void getNodeText(Node* node, std::string& text) const;
    Tree();
    ~Tree();
    Tree(const Tree& other);
    Tree& operator=(const Tree& other);
    Tree operator+(const Tree& other) const;
    void enter(const std::vector<std::string>& tokens);
    std::string toString() const;
    std::vector<std::string> getVars() const;
    double comp(const std::vector<double>& values) const;
};

#endif