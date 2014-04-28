#include <Python.h>

int main()
{
    Py_Initialize();
    PyRun_SimpleString("execfile('bize.py')");
    Py_Finalize();
    return 0;
}      
