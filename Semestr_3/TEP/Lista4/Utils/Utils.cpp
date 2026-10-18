#include "Utils.h"

std::vector<std::string> Utils::split(const std::string &input, char delimiter) {
    std::vector<std::string> list;
    std::string current;

    for (int i = 0; i < input.length(); ++i) {
        if (input[i] == delimiter) {
            if (!current.empty()) {
                list.push_back(current);
                current = "";
            }
        } else {
            current += input[i];
        }
    }

    if (!current.empty()) {
        list.push_back(current);
    }

    return list;
}
