This file defines a class `Timer` to record multiple running time.

```python
import numpy as np
import time


class Timer:  # @save
    """记录多次运行时间"""

    def __init__(self):
        self.times = []
        self.tik
        self.start()

    def start(self):
        """启动计时器"""
        self.tik = time.time()

    def stop(self):
        """停止计时器并将时间记录在列表中"""
        self.times.append(time.time() - self.tik)
        return self.times[-1]

    def avg(self):
        """返回平均时间"""
        return sum(self.times) / len(self.times)

    def sum(self):
        """返回时间总和"""
        return sum(self.times)

    def cumsum(self):
        """返回累计时间"""
        return np.array(self.times).cumsum().tolist()
```

The class `Timer` has the following methods:

- `__init__()`: initialize the timer.
- `start()`: start the timer.
- `stop()`: stop the timer and record the time in the list.
- `avg()`: return the average time.
- `sum()`: return the total time.
- `cumsum()`: return the cumulative time.
