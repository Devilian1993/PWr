#include "Table.h"

#include <iostream>
#include <ostream>
#include <string>

Table::Table() {
    name = TABLE_DEFAULT_NAME;
    table = new int[TABLE_DEFAULT_SIZE];
    size = TABLE_DEFAULT_SIZE;
    std::cout << "bezp: '" << name << "'\n";
};

Table::Table(std::string tableName, int tableLen) {
    name = tableName;
    tableLen = tableLen >= 0 ? tableLen : TABLE_DEFAULT_SIZE;
    table = new int[tableLen];
    size = tableLen;

    std::cout << "parametr: '" << name << "'\n";
}

Table::Table(const Table &other) {
    name = other.name + "_copy";
    size = sizeof(other.table);
    table = new int[size];
    for (int i = 0; i < size; i++) {
        table[i] = other.table[i];
    }

    std::cout << "kopiuj: '" << other.name << "'\n";
}

Table::~Table() {
    std::cout << "usuwam: '" << name << "'\n";
    delete[] table;
}

void Table::setName(std::string newName) {
    name = newName;
}

bool Table::setNewSize(int newSize) {
    if (newSize < 0) {
        std::cout << "Nie można ustawić rozmiaru tablicy < 0!\n";
        return false;
    }
    int effectiveSize = std::min(size, newSize);
    int* newTable = new int[newSize];

    for (int i = 0; i < effectiveSize; i++) {
        newTable[i] = table[i];
    }

    delete[] table;
    table = newTable;
    size = newSize;

    return true;
}

Table *Table::clone() {
    return new Table(*this);
}

void modTab(Table* table, int newSize) {
    table->setNewSize(newSize);
}

void modTab(Table table, int newSize) {
    table.setNewSize(newSize);
}

void Table::fill() {
    for (int i = 0; i < size; i++) {
        table[i] = i % 2 == 0 ? i * (-1) : i;
    }
}

void Table::printContent() {
    std::cout << "[ ";
    for (int i = 0; i < size; i++) {
        std::cout << table[i] << " ";
    }
    std::cout << "]\n";
}

Table Table::extractNegative() {
    Table negativeTable;
    int negativeSize = 0;

    for (int i = 0; i < size; i++) {
        if (this->table[i] < 0) {
            ++negativeSize;
        }
    }

    negativeTable.setNewSize(negativeSize);

    int negativeIndex = 0;
    int nonNegativeIndex = 0;
    for (int i = 0; i < size; i++) {
        if (table[i] < 0) {
            negativeTable.table[negativeIndex++] = table[i];
        } else {
            this->table[nonNegativeIndex++] = table[i];
        }
    }

    setNewSize(nonNegativeIndex);

    return negativeTable;
}


