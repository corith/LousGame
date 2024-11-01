#!/usr/bin/env python3

import subprocess
import os
import shutil
import string
import time

os.system('javac LousReady.java')
try:
    result = subprocess.run(['java', 'LousReady'] , stdout=subprocess.PIPE, timeout=4)
    output = result.stdout.decode('utf-8')
except:
    result = 1;
    print("timed out")
    output = "not game over"
count = 0
while output.find("GAME OVER") == -1:
    count+=1
    try:
        result = subprocess.run(['java', 'LousReady'] , stdout=subprocess.PIPE, timeout=5)
        output = result.stdout.decode('utf-8')
    except:
        result = 1
        print("timed out")
        if count == 10:
           break 

print(output)
print("program ran: = " , count)
os.system('mv *.class ../out/')


