#ifndef NODE_H
#define NODE_H

#include <vector>
#include <string>
#include <map>

class Node {
protected:
    std::vector<Node*> children;

public:
    virtual ~Node();

    virtual int numberOfArguments() const = 0;
    virtual double evaluate(const std::map<std::string, double>& vars) const = 0;
    virtual std::string toString() const = 0;
    bool isLeaf() const;
    virtual Node* clone() const = 0;

    void addChild(Node* child);
    std::vector<Node*>& getChildren();
};


class ConstantNode : public Node {
private:
    double value;
public:
    ConstantNode(double val);
    int numberOfArguments() const override;
    double evaluate(const std::map<std::string, double>& vars) const override;
    std::string toString() const override;
    Node* clone() const override;
};

class VariableNode : public Node {
private:
    std::string name;
public:
    VariableNode(std::string n);
    int numberOfArguments() const override;
    double evaluate(const std::map<std::string, double>& vars) const override;
    std::string toString() const override;
    Node* clone() const override;
};


class AdditionNode : public Node {
public:
    AdditionNode();
    int numberOfArguments() const override;
    double evaluate(const std::map<std::string, double>& vars) const override;
    std::string toString() const override;
    Node* clone() const override;
};

class SubtractionNode : public Node {
public:
    SubtractionNode();
    int numberOfArguments() const override;
    double evaluate(const std::map<std::string, double>& vars) const override;
    std::string toString() const override;
    Node* clone() const override;
};

class MultiplicationNode : public Node {
public:
    MultiplicationNode();
    int numberOfArguments() const override;
    double evaluate(const std::map<std::string, double>& vars) const override;
    std::string toString() const override;
    Node* clone() const override;
};

class DivisionNode : public Node {
public:
    DivisionNode();
    int numberOfArguments() const override;
    double evaluate(const std::map<std::string, double>& vars) const override;
    std::string toString() const override;
    Node* clone() const override;
};


class SinNode : public Node {
public:
    SinNode();
    int numberOfArguments() const override;
    double evaluate(const std::map<std::string, double>& vars) const override;
    std::string toString() const override;
    Node* clone() const override;
};

class CosNode : public Node {
public:
    CosNode();
    int numberOfArguments() const override;
    double evaluate(const std::map<std::string, double>& vars) const override;
    std::string toString() const override;
    Node* clone() const override;
};

#endif