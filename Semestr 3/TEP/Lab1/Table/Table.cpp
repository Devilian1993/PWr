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
    table = new int[tableLen];
    size = tableLen;

    std::cout << "parametr: '" << name << "'\n";
}

Table::Table(Table &other) {
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
    int* newTable = new int[effectiveSize];

    for (int i = 0; i < effectiveSize; i++) {
        newTable[i] = table[i];
    }

    delete[] table;
    table = newTable;
    size = effectiveSize;

    return true;
}

Table *Table::clone() {
    return new Table(*this);
}

void mod_tab(Table* table, int newSize) {
    table->setNewSize(newSize);
}

void mod_tab(Table table, int newSize) {
    table.setNewSize(newSize);
}


