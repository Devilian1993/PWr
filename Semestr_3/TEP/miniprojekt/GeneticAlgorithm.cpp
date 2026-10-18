//
// Created by devilian1993 on 27/01/2026.
//

#include "GeneticAlgorithm.h"

#include <iostream>
#include <algorithm> // dla std::min

GeneticAlgorithm::GeneticAlgorithm(int populationSize, double mutationProbability, double crossoverProbability,
                                   int maxIterations, int numberOfGroups, Evaluator &evaluator)
    : populationSize(populationSize),
      mutationProbability(mutationProbability),
      crossoverProbability(crossoverProbability),
      maxIterations(maxIterations),
      numberOfGroups(numberOfGroups),
      evaluator(evaluator)
{
    std::random_device rd;
    rng.seed(rd());

    bestIndividual = Individual();
}

void GeneticAlgorithm::run() {
    initializePopulation();

    if (population.empty()) {
        throw std::runtime_error("Running algorithm on an empty population");
    }

    for (int i = 0; i < maxIterations; ++i) {
        runIteration();
    }
}

void GeneticAlgorithm::initializePopulation() {
    population.clear();
    population.reserve(populationSize);

    int genotypeSize = evaluator.getNumberOfCustomers();

    while (population.size() < populationSize) {
        Individual newInd(genotypeSize, numberOfGroups, rng);
        newInd.updateFitness(evaluator);

        if (newInd.getFitness() > 0) {
            population.push_back(newInd);

            if (bestIndividual.getFitness() < 0 || newInd.getFitness() < bestIndividual.getFitness()) {
                bestIndividual = newInd;
            }
        }
    }
}

void GeneticAlgorithm::runIteration() {
    std::vector<Individual> newPopulation;
    newPopulation.reserve(populationSize);

    newPopulation.push_back(bestIndividual);

    while (newPopulation.size() < populationSize) {
        Individual& parent1 = selectParentTournament();
        Individual& parent2 = selectParentTournament();

        std::pair<Individual, Individual> children = parent1.crossover(parent2, crossoverProbability, rng);

        children.first.mutate(mutationProbability, numberOfGroups, rng);
        children.second.mutate(mutationProbability, numberOfGroups, rng);

        children.first.updateFitness(evaluator);
        children.second.updateFitness(evaluator);

        newPopulation.push_back(children.first);

        if (newPopulation.size() < populationSize) {
            newPopulation.push_back(children.second);
        }

        if (children.first.getFitness() > 0 &&
           (bestIndividual.getFitness() < 0 || children.first.getFitness() < bestIndividual.getFitness())) {
            bestIndividual = children.first;
        }
        if (children.second.getFitness() > 0 &&
           (bestIndividual.getFitness() < 0 || children.second.getFitness() < bestIndividual.getFitness())) {
            bestIndividual = children.second;
        }
    }

    population = newPopulation;
}

Individual& GeneticAlgorithm::selectParentTournament() {
    std::uniform_int_distribution<> dist(0, populationSize - 1);

    Individual& candidate1 = population[dist(rng)];
    Individual& candidate2 = population[dist(rng)];

    double fitness1 = candidate1.getFitness();
    double fitness2 = candidate2.getFitness();


    return fitness1 < fitness2 ? candidate1 : candidate2 ;
}