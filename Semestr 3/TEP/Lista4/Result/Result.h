
#ifndef LISTA4_RESULT_H
#define LISTA4_RESULT_H
#include <vector>


template <typename T, typename E>
class Result
{
public:
    Result(const T& value);
    Result(E* error);
    Result(const std::vector<E*>& errors);
    Result(const Result<T, E>& other);
    ~Result();
    static Result ok(const T& value);
    static Result fail(E* error);
    static Result fail(std::vector<E*>& errors);
    Result& operator=(const Result<T, E>& other);
    bool isSuccess();
    T getValue();
    std::vector<E*>& getErrors();
private:
    T *value;
    std::vector<E*> errors;
    bool success;
};

template<typename T, typename E>
Result<T, E>::Result(const T &value) : value(value), success(true) {
}

template<typename T, typename E>
Result<T, E>::Result(E *error) : value(), errors{ error }, success(false) {
}

template<typename T, typename E>
Result<T, E>::Result(const std::vector<E *> &errors) : value(), errors(errors), success(false) {
}

template<typename T, typename E>
Result<T, E>::Result(const Result<T, E> &other) : value(other.value), success(other.success) {
    for (E *error : other.errors) {
        errors.push_back(new E(error));
    }
}

template<typename T, typename E>
Result<T, E>::~Result() {
    for (const E *error : errors) {
        delete error;
    }
}

template<typename T, typename E>
Result<T, E> Result<T, E>::ok(const T &value) {
}

template<typename T, typename E>
Result<T, E> Result<T, E>::fail(E *error) {
}

template<typename T, typename E>
Result<T, E> Result<T, E>::fail(std::vector<E *> &errors) {
}

template<typename T, typename E>
Result<T, E> & Result<T, E>::operator=(const Result<T, E> &other) {
}

template<typename T, typename E>
bool Result<T, E>::isSuccess() {
}

template<typename T, typename E>
T Result<T, E>::getValue() {
}

template<typename T, typename E>
std::vector<E *> & Result<T, E>::getErrors() {
}


#endif
