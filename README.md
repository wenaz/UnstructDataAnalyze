# UnstructDataAnalyze
UnstructDataAnalyze Web Project.  Use Hadoop,Lucene,SSH etc.


<h4>一、系统功能</h4>
文件管理功能：文件上传、下载、删除；
文件查询功能：提供文件名、文件关键词、文件内容的查询功能；
内容统计功能：词频统计、股票频率（统计股票出现次数和给出排序）、基于时间范围的内容分析（比如统计一段时间内的文件内容信息，需要进行文件的元数据管理）。


<h4>二、系统技术架构</h4>
基于开源lucene技术来实现非结构化数据的索引和文件管理，可以实现文件索引、查询及内容统计功能；但考虑到未来系统的扩展性，研究基于lucene＋mapreduce＋hdfs实现分布式索引和并行内容统计，即底层使用hdfs来做文件数据的分布式存储，中间层采用mapreduce的并行计算来实现内容统计分析。



<h4>三、研发分工</h4>
<ul>
<li>Lucene modele,support multiple format：microsoft office、pdf、txt。using POI and PDFBOX api </li>
<li>file manager modele, base on hdfs</li>
<li>static modele,base on mapreduce</li>
<li>other:spark,solar,springmvc,scala,bootstrap+jquery,php demo transfer</li>
</ul>