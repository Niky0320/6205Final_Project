#!/usr/bin/env python
# coding: utf-8

# In[2]:

import numpy as np
import pandas as pd
import csv
import matplotlib.pyplot as plt
import matplotlib.patches as mpatches
import random

#get_ipython().run_line_magic('matplotlib', 'qt5')
import matplotlib
matplotlib.rcParams['axes.unicode_minus'] = False


#read many files
files =[]
sites=4;
for i in range(1,sites+1):
    #files.append("data/sars/simulation_%d.csv" %i )
    files.append("data/covid-19/simulation_%d.csv" % i)
#for s in files:
 #   print(s);


def plot_this(axes,X,V,color):
    rand = range(0,X,1)
    x= random.sample(rand, V)
    y= random.sample(rand, V)
    return axes.scatter(x,y,color = color,s=5)
    
def plot_isolated(axes,X,V):
    
    x=random.sample(range(X+200,X+1000,1), V)
    y=random.sample(range(X-1300,X-300,1), V)
    return axes.scatter(x,y,color = "blue",s=5)
    
def plot_dead(axes,X,V):
   
    x=random.sample(range(X+200,X+1000,1), V)
    y=random.sample(range(0,500,1), V)
    return axes.scatter(x,y,color = "black",s=5)



#The first row of the data is not used as the column index, and the header is 0 by default;
# index_ Col = false, the first column of the data is not used as the row index# Prevent pop-up warning

    
datas=[]  #4个data

for ff in files:
    csv_data = pd.read_csv(ff, header=None,index_col=False, names=['Sum','Infected','Isolated','Dead','Immuned','Normal'],low_memory = False) 
    data = pd.DataFrame(csv_data)
    datas.append(data)


#datas[0][0:1] #1st row day0




plt.ion()

fp,aa=plt.subplots(2,2,'all','all',figsize=(10, 10))
fp.set_size_inches(15.5,7.5,forward=True)
#label
grey_patch = mpatches.Patch(color='grey', label='Normal')
red_patch = mpatches.Patch(color='red', label='Infected')
blue_patch = mpatches.Patch(color='blue', label='Isolated')
green_patch = mpatches.Patch(color='green', label='Immuned')
black_patch = mpatches.Patch(color='black', label='Dead')
plt.xlim(-100,6100)
plt.ylim(-100,5200)
i=0
j=0

for i in range(0,2):#0,1
    for j in range(0,2):

        aa[i][j].legend(handles=[grey_patch,red_patch,blue_patch,green_patch,black_patch], bbox_to_anchor=(1.05,1.0),loc=2, borderaxespad=0.)

plt.tight_layout(4) #subplot interval
t1=aa[0][0].text(5400,4800,'Day',fontsize=10,ha='center')
t2=aa[0][1].text(5400,4800,'Day',fontsize=10,ha='center')
t3=aa[1][0].text(5400,4800,'Day',fontsize=10,ha='center')
t4=aa[1][1].text(5400,4800,'Day',fontsize=10,ha='center')

aa[0][0].set_title('New York')
aa[0][1].set_title('Boston')
aa[1][0].set_title('Beijing')
aa[1][1].set_title('Shanghai')


arrn = np.array(datas[0]['Normal'])
arrin = np.array(datas[0]['Infected'])
arris = np.array(datas[0]['Isolated'])
arrd = np.array(datas[0]['Dead'])
arrim = np.array(datas[0]['Immuned'])

arrn1 = np.array(datas[1]['Normal'])
arrin1 = np.array(datas[1]['Infected'])
arris1 = np.array(datas[1]['Isolated'])
arrd1 = np.array(datas[1]['Dead'])
arrim1 = np.array(datas[1]['Immuned'])

arrn2 = np.array(datas[2]['Normal'])
arrin2 = np.array(datas[2]['Infected'])
arris2 = np.array(datas[2]['Isolated'])
arrd2 = np.array(datas[2]['Dead'])
arrim2 = np.array(datas[3]['Immuned'])

arrn3 = np.array(datas[3]['Normal'])
arrin3 = np.array(datas[3]['Infected'])
arris3 = np.array(datas[3]['Isolated'])
arrd3 = np.array(datas[3]['Dead'])
arrim3 = np.array(datas[3]['Immuned'])

for j in range(366):
    plt.xlim(-100,6100)
    plt.ylim(-100,5200)

    t1.set_text('Day %d' % j)
    t2.set_text('Day %d' % j)
    t3.set_text('Day %d' % j)
    t4.set_text('Day %d' % j)

    l1 =plot_this(aa[0][0],5000,arrn[j],'grey')
    l11=plot_this(aa[0][1],5000,arrn1[j],'grey')
    l12=plot_this(aa[1][0],5000,arrn2[j],'grey')
    l13=plot_this(aa[1][1],5000,arrn3[j],'grey')

    l2 =plot_this(aa[0][0],5000,arrin[j],'red')
    l21=plot_this(aa[0][1],5000,arrin1[j],'red')
    l22=plot_this(aa[1][0],5000,arrin2[j],'red')
    l23=plot_this(aa[1][1],5000,arrin3[j],'red')

    l3 =plot_isolated(aa[0][0], 5000, arris[j])
    l31=plot_isolated(aa[0][1], 5000, arris1[j])
    l32=plot_isolated(aa[1][0], 5000, arris2[j])
    l33=plot_isolated(aa[1][1], 5000, arris3[j])

    l4 =plot_dead(aa[0][0], 5000, arrd[j])
    l41=plot_dead(aa[0][1], 5000, arrd1[j])
    l42=plot_dead(aa[1][0], 5000, arrd2[j])
    l43=plot_dead(aa[1][1], 5000, arrd3[j])

    l5 =plot_this(aa[0][0], 5000, arrim[j], 'lightgreen')
    l51=plot_this(aa[0][1], 5000, arrim1[j], 'lightgreen')
    l52=plot_this(aa[1][0], 5000, arrim2[j], 'lightgreen')
    l53=plot_this(aa[1][1], 5000, arrim3[j], 'lightgreen')

    plt.pause(0.001) #small-fast

    l1.remove()
    l11.remove()
    l12.remove()
    l13.remove()

    l2.remove()
    l21.remove()
    l22.remove()
    l23.remove()

    l3.remove()
    l31.remove()
    l32.remove()
    l33.remove()

    l4.remove()
    l41.remove()
    l42.remove()
    l43.remove()

    l5.remove()
    l51.remove()
    l52.remove()
    l53.remove()

plt.ioff()
plt.show()

input('Press Enter')