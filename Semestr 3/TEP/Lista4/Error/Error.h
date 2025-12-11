#ifndef LISTA4_ERROR_H
#define LISTA4_ERROR_H
#include <string>


class Error {
    std::string message;
public:
    Error();
    Error(const std::string& message);
    std::string getMessage() const;
};


#endif //LISTA4_ERROR_H