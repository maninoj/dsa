class PriorityQueue:
    def __init__(self, size=10):
        self.size = size
        self.len = 0
        self.rear = size - 1
        self.queue = [0] * size

    def enQueue(self, num):
        if self.len == self.size:
            print("Cannot add anymore elements to the queue")
            return

        self.rear = (self.rear + 1) % self.size
        self.queue[self.rear] = num
        self.len += 1

    def deQueue(self):
        if self.len <= 0:
            print("There is no element to remove")
            return -1

        min_index = 0
        for i in range(1, self.len):
            if self.queue[(self.rear + 1 - i + self.size) % self.size] < self.queue[min_index]:
                min_index = (self.rear + 1 - i + self.size) % self.size

        res = self.queue[min_index]
        for i in range(min_index, self.rear):
            self.queue[i] = self.queue[(i + 1) % self.size]

        self.rear = (self.rear - 1 + self.size) % self.size
        self.len -= 1
        return res

    def print_queue(self):
        for i in range(self.len):
            index = (self.rear - i + self.size) % self.size
            print(self.queue[index], end="  ")
        print()

