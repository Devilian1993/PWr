
#ifndef LAB1_ALLOCATION_H
#define LAB1_ALLOCATION_H
#define VALUE_TO_FILL 34
void allocTableFill34(int size);
bool allocTable2Dim(int ***table, int sizeX, int sizeY);
bool deallocTable2Dim(int ***table, int sizeX, int sizeY);
void printArray(int *table, int size);
void printArray(int **table, int sizeX, int sizeY);

#endif //LAB1_ALLOCATION_H