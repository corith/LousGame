#!/usr/bin/env python3

import subprocess
import os
import shutil
import string

os.system('javac LousReady.java')
try:
    result = subprocess.run(['java', 'LousReady'] , stdout=subprocess.PIPE, timeout=3)
except TimeoutExpired:
    print("timedout")
    
output = result.stdout.decode('utf-8')
count = 0
while output.find("WON") == -1:
    count+=1
    try:
        result = subprocess.run(['java', 'LousReady'] , stdout=subprocess.PIPE, timeout=3)
    except TimedoutExpired:
        print("timedout")
    output = result.stdout.decode('utf-8')

print(output)
print("program ran: = " , count)
os.system('mv *.class ../out/')


