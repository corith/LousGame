#!/usr/bin/env python3

import os
import shutil

os.system('javac LousReady.java')
os.system('java LousReady')
os.system('mv *.class ../class/')
