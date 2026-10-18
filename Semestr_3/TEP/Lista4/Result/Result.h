#ifndef LISTA4_RESULT_H
#define LISTA4_RESULT_H

#include <stdexcept>
#include <vector>
#include <iostream> // Przydaje się czasem, ale nie jest konieczne, jeśli nie debugujesz

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
    
    bool isSuccess() const;
    T getValue() const;
    std::vector<E*>& getErrors();

private:
    T *value;
    std::vector<E*> errors;
    void clear();
};


template<typename T, typename E>
void Result<T, E>::clear() {
    if (value != nullptr) {
        delete value;
        value = nullptr;
    }
    for (E *error : errors) {
        delete error;
    }
    errors.clear();
}


template<typename T, typename E>
Result<T, E>::Result(const T &val) {
    value = new T(val);
}

template<typename T, typename E>
Result<T, E>::Result(E *error) {
    value = nullptr;
    errors.push_back(error);
}

template<typename T, typename E>
Result<T, E>::Result(const std::vector<E *> &errs) {
    value = nullptr;
    for (E *error : errs) {
        errors.push_back(new E(*error));
    }
}

template<typename T, typename E>
Result<T, E>::Result(const Result &other) {
    if (other.value != nullptr) {
        value = new T(*other.value);
    } else {
        value = nullptr;
    }

    for (E *error : other.errors) {
        errors.push_back(new E(*error));
    }
}

template<typename T, typename E>
Result<T, E>::~Result() {
    clear();
}

template<typename T, typename E>
Result<T, E> Result<T, E>::ok(const T &value) {
    return Result(value);
}

template<typename T, typename E>
Result<T, E> Result<T, E>::fail(E *error) {
    return Result(error);
}

template<typename T, typename E>
Result<T, E> Result<T, E>::fail(std::vector<E *> &errors) {
    return Result(errors);
}

template<typename T, typename E>
Result<T, E> & Result<T, E>::operator=(const Result &other) {
    if (this == &other) {
        return *this;
    }
    clear();

    if (other.value != nullptr) {
        value = new T(*other.value);
    } 

    for (E* err : other.errors) {
        errors.push_back(new E(*err));
    }

    return *this;
}

template<typename T, typename E>
bool Result<T, E>::isSuccess() const {
    return value != nullptr && errors.empty();
}

template<typename T, typename E>
T Result<T, E>::getValue() const {
    if (value != nullptr) {
        return *value;
    }

    throw std::logic_error("Calling getValue() on unsuccessful result");
}

template<typename T, typename E>
std::vector<E *> & Result<T, E>::getErrors() {
    return errors;
}

template <typename E>
class Result<void, E>
{
public:
    Result();
    Result(E* error);
    Result(const std::vector<E*>& errors);
    Result(const Result& other);
    ~Result();

    static Result ok();
    static Result fail(E* error);
    static Result fail(std::vector<E*>& errors);

    Result& operator=(const Result& other);

    bool isSuccess() const;
    std::vector<E*>& getErrors();

private:
    std::vector<E*> errors;
    void clear();
};

template<typename E>
void Result<void, E>::clear() {
    for (E *error : errors) {
        delete error;
    }
    errors.clear();
}

template<typename E>
Result<void, E>::Result() = default;

template<typename E>
Result<void, E>::Result(E *error) {
    errors.push_back(error);
}

template<typename E>
Result<void, E>::Result(const std::vector<E *> &errs) {
    for (E *error : errs) {
        errors.push_back(new E(*error));
    }
}

template<typename E>
Result<void, E>::Result(const Result &other) {
    for (E *error : other.errors) {
        errors.push_back(new E(*error));
    }
}

template<typename E>
Result<void, E>::~Result() {
    clear();
}


template<typename E>
Result<void, E> Result<void, E>::ok() {
    return Result();
}

template<typename E>
Result<void, E> Result<void, E>::fail(E *error) {
    return Result(error);
}

template<typename E>
Result<void, E> Result<void, E>::fail(std::vector<E *> &errors) {
    return Result(errors);
}

template<typename E>
Result<void, E> & Result<void, E>::operator=(const Result<void, E> &other) {
    if (this == &other) {
        return *this;
    }
    clear();

    for (E* err : other.errors) {
        errors.push_back(new E(*err));
    }
    return *this;
}

template<typename E>
bool Result<void, E>::isSuccess() const {
    return errors.empty();
}

template<typename E>
std::vector<E *> & Result<void, E>::getErrors() {
    return errors;
}

#endif