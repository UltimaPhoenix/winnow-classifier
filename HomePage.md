# Summary article for entire project (Question Classification and Winnow)
Work in progress


# Abstract #

In this paper we describe an implementation of the winnow algorithm for the question classification problem.
The project's aim is to try the accuracy of a Winnow Based Algorithm in Question Classification Problem.


# Introduction #

## Question Answering (QA) ##

### Why QA? ###
Every people desire a system that could answer to generic question. Just thinking about our browsing session; how many time we search information from www.google.com, and how many time we found web page that doesn't contain the thing/information we are searching.
Evidently the research tool we at our disposal can't satisfy the expectation.
The huge goal of QA is answer to question that the user do in natural language.

### Manage the web Knowledge ###
The web is a large source of information; comparing it to real life, is it like a big library. The problem of this huge library is that it is so big to become unaccessible for any reading.

### Problem of Question Answering ###
A question answering system must resolve a lot of problem:
  * Determinate the hidden concept/information of the subject that do the question (e.g. the location is important, just thinking about a question like "Where is <a city>?", probably are searching a city in the same nation where are)

## Question Classification (QC) ##
What is Question Classification? Who know?
  * QA
  * QC
  * online/offline
  * Mistake Bound Model

# Description of the classifier #

The classifier is conforms to the [WEKA Classifier Interface](http://weka.sourceforge.net/doc/weka/classifiers/Classifier.html).
The basic feature of the classifier are:
  * Winnow based
  * Online

## What is WEKA? ##
WEKA is a powerful and extensible DataMining tool written in Java. It offer a Command Line Interface CLI and a GUI for users, it is also a set of tools (in only one jar) for developers.

### General Weka Structure ###
First of all we should have a general perspective of the Weka structure and of its feature.
Weka can read for natively .arff file, this file must contain the dataset to process, for example it can be the train dataset of our classifier. A .arff file should respect a strict and human readable structure, this can be viewed like a limitation but we can create a personalized parser which deals to build the Instances object. An Instances is an object that represent a dataset, independently of his data source representation (database, arff file, each other kind representation).

## WinnowClassifier ##
The structure of the classifier is the following;
the main class is named WinnowClassifier and perform all the feature of a WEKA Classifier.
To satisfy the prerequisite of the Question Classification problem the WinnowClassifier class contains a map of SpecialistCollection object, respectively, one for each category to classify.
A SpecialistCollection is conceptually a set of Specialist that collaborate to classify the instances. The SpecialistCollection makes a positive or negative prediction, that is based on the sum's weights of the specialists contained in it.
The Specialist object contained in the SpecialistCollection, have a weight that raise or decrease every time the prediction is wrong. On positive prediction we decrease the weights of the Specialists, vice versa on negative prediction we decrease the weights.



# Experimental result #

All the test are made on macro category of:
  * train\_5500.label on training and cross validation
  * TREC\_10.label on test validation
To compare our classifier with the default ["winnow Weka"](http://weka.sourceforge.net/doc/weka/classifiers/functions/Winnow.html)we have should build a wrapper that encapsulate six instance of the Weka classifier, on for each macro category, this because the winnow algorithm can classify only one category at a time.

## Winnow Weka Collector vs Winnow Classifier ##
~75%

### Winnow Weka Collector vs Winnow Classifier with more Filter ###
~80%

# Conclusion #

Don't ask us nothing!

# References #
  * [WEKA Project Site](http://www.cs.waikato.ac.nz/ml/weka/)
  * [WEKA JavaDoc](http://weka.sourceforge.net/doc/index.html)
  * Machine Learning - Tom Mitchell
  * Data Mining Practical - Machine Learning Tools And Techniques 2Ed - Ian Witten Eibe Frank
  * we ^^