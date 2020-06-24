#!/usr/bin/env python3

import os
import shutil

os.system('javac LousReady.java')
os.system('java LousReady')
if not os.path.exists('../out'):
    os.system('mkdir ../out/')
os.system('mv *.class ../out/')

