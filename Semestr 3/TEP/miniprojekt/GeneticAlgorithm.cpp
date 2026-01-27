//
// Created by devilian1993 on 27/01/2026.
//

#include "GeneticAlgorithm.h"

#include <iostream>
#include <algorithm> // dla std::min

using namespace std;

GeneticAlgorithm::GeneticAlgorithm(int populationSize, double mutationProbability, double crossoverProbability,
                                   int maxIterations, int numberOfGroups, Evaluator &evaluator)
    : populationSize(populationSize),
      mutationProbability(mutationProbability),
      crossoverProbability(crossoverProbability),
      maxIterations(maxIterations),
      numberOfGroups(numberOfGroups),
      evaluator(evaluator)
{
    random_device rd;
    rng.seed(rd());

    bestIndividual = Individual();
}

void GeneticAlgorithm::run() {
    cout << "Generowanie populacji poczatkowej..." << endl;
    initializePopulation();

    if (population.empty()) {
        cerr << "BLAD: Nie udalo sie znalezc zadnego poprawnego rozwiazania na start!" << endl;
        return;
    }

    cout << "Start ewolucji. Poczatkowy najlepszy wynik: " << bestIndividual.getFitness() << endl;

    for (int i = 0; i < maxIterations; ++i) {
        runIteration();

        if ((i + 1) % 1000 == 0) {
            cout << "Iteracja " << (i + 1) << " | Najlepszy koszt: " << bestIndividual.getFitness() << endl;
        }
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
    vector<Individual> newPopulation;
    newPopulation.reserve(populationSize);

    while (newPopulation.size() < populationSize) {
        Individual& parent1 = selectParentTournament();
        Individual& parent2 = selectParentTournament();

        pair<Individual, Individual> children = parent1.crossover(parent2, crossoverProbability, rng);

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
    uniform_int_distribution<int> dist(0, populationSize - 1);

    Individual& candidate1 = population[dist(rng)];
    Individual& candidate2 = population[dist(rng)];

    double fitness1 = candidate1.getFitness();
    double fitness2 = candidate2.getFitness();


    if (fitness1 < 0 && fitness2 < 0) return candidate1;

    if (fitness1 < 0) return candidate2;

    if (fitness2 < 0) return candidate1;

    if (fitness1 < fitness2) return candidate1;

    return candidate2;
}