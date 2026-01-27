#include <iostream>
#include <string>
#include "Evaluator.h"
#include "GeneticAlgorithm.h"

using namespace std;

int main() {
    string instancePath = "data/lcvrp/Vrp-Set-A/A-n32-k5.lcvrp";
    int numberOfGroups = 5;

    int populationSize = 100;
    double mutationProbability = 0.03;
    double crossoverProbability = 0.7;
    int maxIterations = 10000;


    Evaluator evaluator;
    cout << "Wczytywanie instancji: " << instancePath << "..." << endl;

    evaluator.load(instancePath);

    cout << "Pomyslnie wczytano dane." << endl;

    GeneticAlgorithm algorithm(populationSize, mutationProbability, crossoverProbability,
                               maxIterations, numberOfGroups, evaluator);

    algorithm.run();


    Individual best = algorithm.getBestSolution();

    cout << "----------------------------------------" << endl;
    cout << "KONIEC SYMULACJI" << endl;

    if (best.getFitness() > 0) {
        cout << "Najlepszy znaleziony koszt: " << best.getFitness() << endl;

        cout << "Genotyp (przydzial do grup): ";
        const vector<int>& genes = best.getGenotype();
        for (int g : genes) {
            cout << g << " ";
        }
        cout << endl;
    } else {
        cout << "Nie udalo sie znalezc poprawnego rozwiazania." << endl;
    }

    return 0;
}