//
// Created by devilian1993 on 27/01/2026.
//

#ifndef MINIPROJEKT_GENETICALGORITHM_H
#define MINIPROJEKT_GENETICALGORITHM_H

#include <vector>
#include <random>
#include "Individual.h"
#include "Evaluator.h"

class GeneticAlgorithm {
public:
    GeneticAlgorithm(int populationSize, double mutationProbability, double crossoverProbability,
                     int maxIterations, int numberOfGroups, Evaluator &evaluator);

    void run();

    Individual getBestSolution() const { return bestIndividual; }

private:
    int populationSize;
    double mutationProbability;
    double crossoverProbability;
    int maxIterations;
    int numberOfGroups;

    std::vector<Individual> population;
    Individual bestIndividual;

    Evaluator &evaluator;
    std::mt19937 rng;

    void initializePopulation();
    void runIteration();
    Individual& selectParentTournament();
};


#endif //MINIPROJEKT_GENETICALGORITHM_H