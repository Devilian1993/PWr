//
// Created by devilian1993 on 12/10/2025.
//

#ifndef LAB1_TABLE_H
#define LAB1_TABLE_H

#define TABLE_DEFAULT_SIZE 16
#define TABLE_DEFAULT_NAME "default"
#include <string>

class Table {
    private:
        std::string name;
        int* table;
        int size;

    public:
        Table();
        Table(std::string name, int size);
        Table(Table& other);
        ~Table();
        void setName(std::string name);
        bool setNewSize(int size);
        Table* clone();
};

#endif //LAB1_TABLE_H