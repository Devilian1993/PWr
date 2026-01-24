//
// Created by Maks on 23.01.2026.
//

#ifndef MINIPROJEKT_PROBLEM_H
#define MINIPROJEKT_PROBLEM_H
#include <string>
#include <vector>

struct Point {
    double x;
    double y;
};

class Evaluator {
    public:
    Evaluator();
    void load(const std::string& filename);
    double getFitness(const std::vector<int>& genotype);

    private:
    std::string problemName;
    int dimension{};
    int capacity{};
    double distance{};
    std::vector<int> permutation;
    std::vector<Point> nodes;
    std::vector<int> demand;

    const double WRONG_VAL = -1.0;

    double getDistance(int node_1, int node_2) const;
    int parseInt(const std::string& line);
    double parseDouble(const std::string& line);
    std::string parseString(const std::string& line);
    std::vector<int> parsePermutation(const std::string& line);
    std::vector<Point> parseNodes(std::ifstream& file);
    std::vector<int> parseDemand(std::ifstream& file);
};


#endif //MINIPROJEKT_PROBLEM_H