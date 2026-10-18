#ifndef LISTA3TEP_UTILS_H
#define LISTA3TEP_UTILS_H
#include <string>
#include <vector>


class Utils {
public:
    static std::vector<std::string> split(const std::string& input, char delimiter = ' ');
};


#endif