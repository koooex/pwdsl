from ctypes import *

base = cdll.LoadLibrary('base.so')
add = base.add
minus = base.minus

print add(2, 3)
