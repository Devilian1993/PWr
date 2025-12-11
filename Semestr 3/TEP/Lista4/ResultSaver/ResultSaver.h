#ifndef RESULT_SAVER_H
#define RESULT_SAVER_H

#include <fstream>
#include <string>
#include <vector>
#include "../Result/Result.h"
#include "../Tree/Tree.h"


template <typename T, typename E>
class ResultSaver {
public:
    static void saveToFile(Result<T, E>& result, const std::string& filename) {
        std::ofstream file(filename);

        if (!file.is_open()) {
            return;
        }

        if (!result.isSuccess()) {
            for (E* error : result.getErrors()) {
                file << error->getMessage() << std::endl;
            }
        }

        file.close();
    }
};


template <typename E>
class ResultSaver<Tree*, E> {
public:
    static void saveToFile(Result<Tree*, E>& result, const std::string& filename) {
        std::ofstream file(filename);

        if (!file.is_open()) {
            return;
        }

        if (!result.isSuccess()) {
            for (E* error : result.getErrors()) {
                file << error->getMessage() << std::endl;
            }
        } else {
            Tree* tree = result.getValue();
            if (tree != nullptr) {
                file << tree->toString();
            }
        }

        file.close();
    }
};

#endif