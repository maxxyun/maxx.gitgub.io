import numpy as np
import matplotlib.pyplot as plt

t = np.arange(0, 10, 0.1) # 0 0.1 0.2 .... 9.9
amp = np.sin(t)  # amplitude

plt.plot(t, amp)


