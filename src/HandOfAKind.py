#!/usr/bin/env python3

import subprocess
import os
import shutil
import string

os.system('javac Hand.java')
result = subprocess.run(['java', 'HandTest'] , stdout=subprocess.PIPE)
output = result.stdout.decode('utf-8')
count = 0
while output.find("yes") == -1:
    count+=1
    result = subprocess.run(['java', 'HandTest'] , stdout=subprocess.PIPE)
    output = result.stdout.decode('utf-8')

print(output)
print("attempts =" , count)
os.system('mv *.class ../out/')


