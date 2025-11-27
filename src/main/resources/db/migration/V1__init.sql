CREATE TABLE posts
(
    id      BIGSERIAL PRIMARY KEY,
    title   VARCHAR(255) NOT NULL,
    content TEXT         NOT NULL,
    created TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    likes   INTEGER      NOT NULL DEFAULT 0,
    UNIQUE (title)
);

INSERT INTO posts (title, content, created, likes)
VALUES ('Introduction to PostgreSQL',
        'PostgreSQL is a powerful open-source object-relational database management system. In this article, we will explore the main features and advantages of PostgreSQL.',
        '2024-01-15 10:30:00',
        142),

       ('How SQL Indexing Works',
        'Database indexes help speed up data retrieval. Let''s examine different types of indexes: B-tree, Hash, GiST, and GIN, and when to use them.',
        '2024-02-20 14:22:00',
        89),

       ('REST API Best Practices 2024',
        'Designing a good REST API requires understanding RESTful architecture principles. This article covers naming conventions, versioning, error handling, and authentication.',
        '2024-03-10 09:15:00',
        256),

       ('Docker for Beginners',
        'Docker has revolutionized the way applications are deployed. Learn how to create containers, work with images, and orchestrate multi-container applications using Docker Compose.',
        '2024-04-05 16:45:00',
        312),

       ('Sorting Algorithms Performance Comparison',
        'A detailed analysis of popular sorting algorithms: QuickSort, MergeSort, HeapSort, and ShellSort. We test performance on various datasets and choose the optimal algorithm.',
        '2024-05-12 11:00:00',
        198);