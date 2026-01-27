//
// Created by Maks on 23.01.2026.
//

#include "Evaluator.h"

#include <cmath>
#include <fstream>
#include <csetjmp>
#include <sstream>

Evaluator::Evaluator() = default;

int Evaluator::getNumberOfCustomers() const {
    return (dimension > 0) ? dimension - 1 : 0;
}

int Evaluator::getCapacity() const {
    return capacity;
}

double Evaluator::getDistance(int node_1, int node_2) const {
    if (node_1 < 0 || node_2 >= nodes.size() || node_2 < 0 || node_2 >= nodes.size()) {
        throw std::runtime_error("Index out of bounds");
    }

    double dx = nodes[node_1].x - nodes[node_2].x;
    double dy = nodes[node_1].y - nodes[node_2].y;
    return std::sqrt(dx * dx + dy * dy);
}

int Evaluator::parseInt(const std::string &line) {
    int colonPos = line.find(':');
    if (colonPos != std::string::npos) {
        return std::stoi(line.substr(colonPos + 1));
    }

    throw std::runtime_error("Invalid problem file");
}

double Evaluator::parseDouble(const std::string &line) {
    int colonPos = line.find(':');
    if (colonPos != std::string::npos) {
        return std::stod(line.substr(colonPos + 1));
    }

    throw std::runtime_error("Invalid problem file");
}

std::string Evaluator::parseString(const std::string &line) {
    int colonPos = line.find(':');
    if (colonPos != std::string::npos) {
        return line.substr(colonPos + 1);
    }

    throw std::runtime_error("Invalid problem file");
}

std::vector<int> Evaluator::parsePermutation(const std::string &line) {
    std::vector<int> result;
    size_t colonPos = line.find(':');
    if (colonPos == std::string::npos) return result;

    std::stringstream ss(line.substr(colonPos + 1));
    int val;
    while (ss >> val) {
        if (val < 0) {
            throw std::runtime_error("Invalid permutation in problem file");
        }
        result.push_back(val);
    }

    return result;
}

std::vector<Point> Evaluator::parseNodes(std::ifstream &file) {
    std::vector<Point> result;
    for (int i = 0; i < dimension; i++) {
        int id;
        double x, y;
        file >> id >> x >> y;

        if (id < 1 || id > dimension) {
            throw std::runtime_error("Invalid node id in problem file");
        }

        if(id-1 < dimension) {
            result.push_back({x, y});
        }
    }

    return result;
}

std::vector<int> Evaluator::parseDemand(std::ifstream &file) {
    std::vector<int> result;
    for (int i = 0; i < dimension; i++) {
        int id, dem;
        file >> id >> dem;

        if (id < 0 || id > dimension || dem < 0) {
            throw std::runtime_error("Invalid demands in problem file");
        }
        if(id-1 < dimension) {
            result.push_back(dem);
        }
    }

    return result;
}

void Evaluator::load(const std::string& filename) {
    std::ifstream file(filename);

    if (!file.is_open()) {
        throw std::runtime_error("Cannot open file");
    }

    std::string line;
    while (std::getline(file, line)) {
        std::string type;
        std::stringstream stream(line);

        if (line.find("NAME") != std::string::npos) {
            problemName = parseString(line);
        } else if (line.find("DIMENSION") != std::string::npos) {
            dimension = parseInt(line);
        } else if (line.find("CAPACITY") != std::string::npos) {
            capacity = parseInt(line);
        } else if (line.find("DISTANCE") != std::string::npos) {
            distance = parseDouble(line);
        } else if (line.find("PERMUTATION") != std::string::npos) {
            permutation = parsePermutation(line);
        } else if (line.find("NODE_COORD_SECTION") != std::string::npos) {
            nodes = parseNodes(file);
        } else if (line.find("DEMAND_SECTION") != std::string::npos) {
            demand = parseDemand(file);
        }
    }

    file.close();
}

double Evaluator::getFitness(const std::vector<int>& genotype) {
    if (genotype.size() != dimension - 1) {
        throw std::runtime_error("Invalid genotype size");
    }

    double totalDistance = 0.0;

    int maxGroup = 0;
    for (int g : genotype) {
        if (g > maxGroup) maxGroup = g;
    }
    std::vector<std::vector<int>> routes(maxGroup + 1);

    for (int customerId : permutation) {
        // -1 for depot -1 for 0 indexing
        int genotypeIndex = customerId - 2;

        if (genotypeIndex < 0 || genotypeIndex >= genotype.size()) {
            throw std::runtime_error("Invalid genotype index");
        }

        int assignedGroup = genotype[genotypeIndex];

        int nodeIndex = customerId - 1;

        routes[assignedGroup].push_back(nodeIndex);
    }

    for (const auto &route : routes) {
        if (route.empty()) continue;

        double routeDistance = 0.0;
        int currentLoad = 0;
        int lastNode = 0;

        for (int customerIdx : route) {
            currentLoad += demand[customerIdx];
            if (currentLoad > capacity) {
                return WRONG_VAL; 
            }
            routeDistance += getDistance(lastNode, customerIdx);

            lastNode = customerIdx;
        }

        routeDistance += getDistance(lastNode, 0);

        if (currentLoad > capacity) {
            routeDistance += (currentLoad - capacity) * 100.0;
        }

        totalDistance += routeDistance;
    }

    return totalDistance;
}
