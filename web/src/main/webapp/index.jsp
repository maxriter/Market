<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="Max Lysenko">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">

    <title>Market Application</title>

    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="/resources/core/my-style.css">

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">

        <div class="navbar-header">
            <a class="navbar-brand">Market Application</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse">
            <ul class="nav navbar-nav">
                <li>
                    <a href="#about" id="about"><span class="glyphicon glyphicon-info-sign"></span>About</a>
                </li>
            </ul>
        </div>

    </div>

</nav>

<div class="container">
    <div class="page-header text-centred">
    </div>
    <div class="row">

        <div class="col-md-3">
            <h3><span class="glyphicon glyphicon-stats"></span>Market Statistics:</h3>
            <ul class="list-group">
                <li class="list-group-item"><span class="badge" id="good-count"></span> Count of goods</li>
                <li class="list-group-item"><span class="badge" id="max-price"></span> Max price</li>
                <li class="list-group-item"><span class="badge" id="min-price"></span> Min price</li>
                <li class="list-group-item"><span class="badge" id="avg-price"></span> Avg price</li>
            </ul>
            <button type="button" class="btn btn-primary" id="update-statistics"><span
                    class="glyphicon glyphicon-refresh"></span>Refresh
            </button>
        </div>

        <div class="col-md-9">
            <div class="row">
                <div class="inline">
                    <h3><span class="label label-info" id="response-message-span">Info</span></h3>
                </div>
                <div class="inline">
                    <h3 id="response-message">
                        Welcome to Market Application! </h3>
                </div>
            </div>
            <p>
            </p>
            <form class="form-inline">
                <div class="form-group">
                    <input type="text" class="form-control" id="create-good-name" placeholder="Name" >
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="create-good-price" placeholder="Price" >
                </div>
                <button type="button" class="btn btn-success" id="add-good">
                    Add Good
                </button>
            </form>
            <p>
            </p>
            <h3><span class="glyphicon glyphicon-search"></span>Search panel</h3>
            <form class="form-inline" role="form">
                <div class="form-group">
                    <input type="text" class="form-control" id="id" placeholder="ID">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="name" placeholder="Name">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="price-from" placeholder="Price from">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="price-to" placeholder="Price to">
                </div>
                <p>
                </p>
                <button type="button" class="btn btn-primary" id="search">Search</button>
                <button type="button" class="btn btn-primary" id="clear-good-table">Clear table</button>
            </form>

            <h3 class="glyphicon glyphicon-list"> Goods table <h3/>
                <div class="col-md-9">
                    <table class="table table-hover" id="good-table">
                        <tbody>
                        <tr id="all-good-table-first-row">
                            <td>ID</td>
                            <td>Name</td>
                            <td>Price</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-9">
        </div>
    </div>

    <!-- Modal About-->
    <div class="modal fade" id="about-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">About</h4>
                </div>
                <div class="modal-body">
                    <dl>
                        <dt>Market Statistics</dt>
                        <dd>- displays Count of goods, Max price, Min price, Avg price of all goods from the database.
                            When you add a new good or change the price of good Market Statistics is updated.
                        </dd>
                        <dt>How add a good ?</dt>
                        <dd>- You need fill out at least Name and click Add Good (if you don't specify Price it'll be
                            0)
                        </dd>
                        <dt>How update Name or Price of good ?</dt>
                        <dd>- You need to use double-click on Name or Price you want to change</dd>
                        <dt>Refresh</dt>
                        <dd>- update Market Statistics</dd>
                        <dt>Find by</dt>
                        <dd>- select search options, what kind of values will be use for search</dd>
                        <dt>Search</dt>
                        <dd>- click for start searching</dd>
                        <dt>Delete</dt>
                        <dd>- use it for delete good from table</dd>
                        <dt>Clear</dt>
                        <dd>- Table clear table of goods</dd>
                    </dl>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal for update good-->
    <div class="modal fade" id="update-good-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Please, fill out the form</h4>
                </div>
                <div class="modal-body">
                    <div class="form ">
                        <div class="form-group">
                            <label for="newName">Name</label>
                            <input type="text" class="form-control" id="newName"
                                   placeholder="Name">
                            <label for="newPrice">Price</label>
                            <input type="text" class="form-control" id="newPrice"
                                   placeholder="Price">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" id="update">Update</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal remove good dialog-->
    <div class="modal fade" id="delete-good-modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title">Delete good</h4>
                </div>
                <div class="modal-body">
                    <div class="form">
                        <div class="form-group">
                            <h3>Do you want to delete a good?</h3>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" id="delete">Yes</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                </div>
            </div>
        </div>
    </div>


    <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/core/my-script.js"></script>

</body>

</html>

