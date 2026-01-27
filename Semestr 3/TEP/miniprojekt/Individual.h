//
// Created by devilian1993 on 27/01/2026.
//

#ifndef MINIPROJEKT_INDIVIDUAL_H
#define MINIPROJEKT_INDIVIDUAL_H


#include <vector>
#include <random>
#include <utility>

#include "Evaluator.h"

class Evaluator;

class Individual {
public:
    Individual();
    Individual(int genotypeSize, int numberOfGroups, std::mt19937 &rng);
    Individual(const std::vector<int> &genotype);

    double updateFitness(Evaluator &evaluator);
    void mutate(double mutationProbability, int numberOfGroups, std::mt19937 &rng);
    std::pair<Individual, Individual> crossover(const Individual &partner, double crossoverProbability, std::mt19937 &rng);


    double getFitness() const { return fitness; }
    const std::vector<int>& getGenotype() const { return genotype; }

    void setFitness(double newFitness) { fitness = newFitness; }

private:
    std::vector<int> genotype;
    double fitness;
};


#endif //MINIPROJEKT_INDIVIDUAL_H