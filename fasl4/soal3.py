class TwoQueues:
    def __init__(self, size1=50, size2=50):
        self.full = size1 + size2
        self.data = [0] * self.full
        self.size1 = size1
        self.f1 = 0
        self.f2 = size1
        self.r1 = -1
        self.r2 = size1 - 1

    def enQueue(self, num, queueNum):
        if queueNum == 1:
            if (self.r1 + 1) >= self.size1:
                print("queue 1 is full.")
                return
            self.r1 += 1
            self.data[self.r1] = num
        elif queueNum == 2:
            if (self.r2 + 1) >= self.full:
                print("queue 2 is full.")
                return
            self.r2 += 1
            self.data[self.r2] = num

    def deQueue(self, queueNum):
        if queueNum == 1:
            if self.r1 < self.f1:
                print("queue 1 is empty.")
                return None
            res = self.data[self.f1]
            self.f1 += 1
            return res
        elif queueNum == 2:
            if self.r2 < self.f2:
                print("queue 2 is empty.")
                return None
            res = self.data[self.f2]
            self.f2 += 1
            return res

    def print_queues(self):
        print("queue 1:", end=" ")
        for i in range(self.f1, self.r1 + 1):
            print(self.data[i], end=" ")
        print()

        print("queue 2:", end=" ")
        for i in range(self.f2, self.r2 + 1):
            print(self.data[i], end=" ")
        print()

