# Agile
For the Excersise have used Spring Boot with Mongo DB as a Cache.
You should create a Mongo Database called images.
I have added Swagger as well http://localhost:8080/swagger-ui.html

The endpoins are: (examples)

GET /search/camera=Nikon%20D810

response
[{
"id": "5cb0f7bff18c34d3b7fb",
"author": "Incomplete Depth",
"camera": "Nikon D810",
"tags": "#life #whataview #photography #beautifulday #wonderfullife ",
"cropped_picture": "http://interview.agileengine.com/pictures/cropped/1203855203_002.jpg",
"full_picture": "http://interview.agileengine.com/pictures/full_size/1203855203_002.jpg"
},
{
"id": "29d46f953bcb670f8945",
"author": "Quarterly Aside",
"camera": "Nikon D810",
"tags": "#wonderfullife #photo #nature #photooftheday #life #beautifulday #whataview #greatview #photography ",
"cropped_picture": "http://interview.agileengine.com/pictures/cropped/26013.JPG",
"full_picture": "http://interview.agileengine.com/pictures/full_size/26013.JPG"
}]


GET /images/dc0c097fe54afa8c3888
response

{
"id": "dc0c097fe54afa8c3888",
"author": "Worn Bitter",
"camera": "Nikon D700",
"tags": "#natureisbeautiful #wonderfulday #beauty #whataview ",
"cropped_picture": "http://interview.agileengine.com/pictures/cropped/03 - WPIX_86.jpg",
"full_picture": "http://interview.agileengine.com/pictures/full_size/03 - WPIX_86.jpg"
}

NOTE:
I couldn't finish the excercise as I wanted.
The solution needs:
- Unit Tests.
- Better error Handling. (I have added //TODOS in the code)
- Request validations. (I have added some //TODOS in the Code)