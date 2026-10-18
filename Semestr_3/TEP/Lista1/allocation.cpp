#include "allocation.h"

#include <iostream>

void printArray(int *table, int size) {
    std::cout << "[ ";
    for (int i = 0; i < size; i++) {
        std::cout << table[i] << " ";
    }
    std::cout << "]\n";
}

void printArray(int **table, int sizeX, int sizeY) {
    for (int i = 0; i < sizeX; i++) {
        for (int j = 0; j < sizeY; j++) {
            table[i][j] = 0;
        }
    }

    for (int i = 0; i < sizeX; i++) {
        for (int j = 0; j < sizeY; j++) {
            std::cout << table[i][j] << " ";
        }
        std::cout << "\n";
    }
}

void allocTableFill34(int size) {
    if (size < 1) {
        std::cout << "Incorrect size" << "\n";
        return;
    }
    int *table = new int[size];

    for (int i = 0; i < size; i++) {
        table[i] = VALUE_TO_FILL;
    }

    printArray(table, size);

    delete[] table;
}

bool checkSizesTable2Dim(int sizeX, int sizeY) {
    if (sizeX < 1) {
        std::cout << "Incorrect size X" << "\n";
    }
    if (sizeY < 1) {
        std::cout << "Incorrect size Y" << "\n";
    }
    if (sizeX < 1 || sizeY < 1) {
        return false;
    }

    return true;
}

bool allocTable2Dim(int ***table, const int sizeX, const int sizeY) {
    if (!checkSizesTable2Dim(sizeX, sizeY)) {
        return false;
    }

    *table = new int*[sizeX];

    for (int i = 0; i < sizeX; i++) {
        (*table)[i] = new int[sizeY];
    }

    return true;
}

bool deallocTable2Dim(int ***table, const int sizeX, const int sizeY) {
    if (!checkSizesTable2Dim(sizeX, sizeY)) {
        return false;
    }

    for (int i = 0; i < sizeX; i++) {
        delete[] (*table)[i];
    }
    delete[] *table;

    return true;
}