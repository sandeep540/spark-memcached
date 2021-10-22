


### Data

* Data is taken from public dataset at - https://www.kaggle.com/intelai/data-analysis-jobs

### memcached - docker

    docker run -d --name memcached-local -p 11211:11211 memcached

    telnet localhost 11211

    get 484698

    ------- value ----

Structure of Key-Value Pairs in memcahed

![image info](https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Hash_table_3_1_1_0_1_0_0_SP.svg/1200px-Hash_table_3_1_1_0_1_0_0_SP.svg.png)