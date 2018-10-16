var id = 0;

var vm = new Vue({
                
                el : "#app",
                methods : {     //   写函数
                    
                    addBook : function(){
                        try {
                            this.book.id = this.books[this.books.length - 1].id + 1;
                        }catch (e) {
                            this.book.id = 1;
                        }finally {
                        }
                        axios({
                            method:'POST',
                            url:'/book/addBook',
                            data:{
                                id:this.book.id,
                                name:this.book.name,
                                author:this.book.author,
                                price:this.book.price
                            }
                        })
                            .then(function (res) {
                                console.log(res.data)
                                vm.books = res.data;
                                vm.book = [];
                            })
                            .catch(function (reason) {
                                console.log(reason.data)
                            })
                    },
                    
                    delBook : function(book){
                        axios({
                            method:'POST',
                            url:'/book/deleteBook',
                            data:{
                                id:book.id,
                            }
                        })
                            .then(function (res) {
                                console.log(res.data)
                                vm.books = res.data;
                            })
                            .catch(function (reason) {
                                console.log(reason.data)
                            })
                    },
                    
        // 修改按钮                
                    updateBook : function(book){
                        $("#add-book").css("display","none");
                        $("#update-book").css("display","block");
                        id = book.id;
                        axios({
                            method:'post',
                            url:'/book/findBook',
                            data:{
                                id:id
                            }
                        })
                            .then(function (res) {
                                vm.book = res.data
                                console.log(res.data)
                            })
                            .catch(function (reason) {
                                console.log(reason.data)
                            })
                    },
        
        // 修改完成后的 确认按钮点击事件
                    updatedBook : function(){
                        var flag = false;
                        this.book.id = id;
                        axios({
                            method:'post',
                            url:'/book/updateBook',
                            data:{
                                id:id,
                                name:this.book.name,
                                author:this.book.author,
                                price:this.book.price
                            }
                        })
                            .then(function (res) {
                                console.log(res.data);
                                vm.books = res.data;
                                vm.book = [];
                            })
                            .catch(function (reason) {
                                console.log(reason.data);
                            });
                    }
                    
                },
                
                // 计算属性（过滤） 查询功能
                
                computed : {
                    
                    filterBooks : function(){
                        var books = this.books;
                        var search = this.search;
                    
                        return books.filter(function(book){
                            return book.name.toLowerCase().indexOf(search.toLocaleLowerCase()) != -1
                        });        
                    },
                    
                },
                                
                data : {
                    book : [{
                        id : 0,
                        author : '',
                        name : '',
                        price : ''                        
                    }],
                    
                    books : [],
                    
                    search : ""      // 查询功能，""中不能加空格，否则默认查询空格
                },

                mounted : function () {
                    var books = this.books;
                    axios({
                        method:'get',
                        url:'/book/findAll'
                    })
                        .then(function (res) {
                            for(var i=0;i<res.data.length;i++){
                                books.splice(i,0,res.data[i]);
                            }
                        })
                        .catch(function(error) {
                            console.log(error.data);
                        });
                }
            })