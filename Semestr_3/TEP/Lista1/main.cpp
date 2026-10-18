#include <iostream>

#include "allocation.h"
#include "Table/Table.h"

int main() {
    std::cout << "Zadanie 1\n\n";
    allocTableFill34(10);

    std::cout << "\nZadanie 2 i 3\n\n";
    int sizeX = 10;
    int sizeY = 10;
    int **table;
    allocTable2Dim(&table, sizeX, sizeY);
    printArray(table, sizeX, sizeY);
    deallocTable2Dim(&table, sizeX, sizeY);

    std::cout << "\nZadanie 4\n\n";
    Table defaultTable = Table();
    Table constructorTable = Table("tablica", 20);

    modTab(constructorTable, 10);
    modTab(&constructorTable, 10);



    std::cout << "\nModyfikacja\n\n";
    defaultTable.fill();
    defaultTable.printContent();
    Table negativeTable = defaultTable.extractNegative();
    defaultTable.printContent();
    negativeTable.printContent();
}
