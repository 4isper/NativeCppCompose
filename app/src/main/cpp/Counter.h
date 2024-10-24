//
// Created by Дмитрий Исаев on 22.10.2024.
//

#ifndef LAB8_COUNTER_H
#define LAB8_COUNTER_H

class Counter {
private:
    int value;

public:
    Counter() : value(0) {}
    explicit Counter(int value) : value(value){}

    void increment() {
        value++;
    }

    void reset() {
        value = 0;
    }

    int getValue() const {
        return value;
    }
};

#endif //LAB8_COUNTER_H
