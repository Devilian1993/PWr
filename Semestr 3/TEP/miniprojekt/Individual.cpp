//
// Created by Maks on 26.01.2026.
//

#include "Individual.h"
#include "Evaluator.h"

Individual::Individual() : fitness(std::numeric_limits<double>::max()) {}

Individual::Individual(const std::vector<int> &genotype) : genotype(genotype), fitness(std::numeric_limits<double>::max()) {}

Individual::Individual(int genotypeSize, int numberOfGroups, std::mt19937 &rng) {
    fitness = std::numeric_limits<double>::max();
    genotype.resize(genotypeSize);

    std::uniform_int_distribution<> distribution(0, numberOfGroups - 1);

    for (int i = 0; i < genotypeSize; ++i) {
        genotype[i] = distribution(rng);
    }
}

double Individual::updateFitness(Evaluator &evaluator) {
    fitness = evaluator.getFitness(genotype);
    return fitness;
}

void Individual::mutate(double mutationProbability, int numberOfGroups, std::mt19937 &rng) {
    std::uniform_real_distribution<> probabilityDist(0.0, 1.0);
    std::uniform_int_distribution<> groupDist(0, numberOfGroups - 1);

    for (int &gene : genotype) {
        if (probabilityDist(rng) < mutationProbability) {
            gene = groupDist(rng);
        }
    }
}

std::pair<Individual, Individual> Individual::crossover(const Individual &partner, double crossoverProbability, std::mt19937 &rng) {
    std::uniform_real_distribution<> probabilityDist(0.0, 1.0);

    if (probabilityDist(rng) >= crossoverProbability) {
        return { *this, partner };
    }

    if (genotype.size() < 2) {
        return { *this, partner };
    }

    std::uniform_int_distribution<> cutDist(1, genotype.size() - 1);
    int cutPoint = cutDist(rng);

    std::vector<int> child1Genotype = this->genotype;
    std::vector<int> child2Genotype = partner.genotype;

    for (size_t i = cutPoint; i < genotype.size(); ++i) {
        child1Genotype[i] = partner.genotype[i];
        child2Genotype[i] = this->genotype[i];
    }

    return { Individual(child1Genotype), Individual(child2Genotype) };
}