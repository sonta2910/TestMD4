let arr;
function findAll() {
    $.ajax({
        url: "http://localhost:8080/employees",
        type: "GET",
        success(data) {
            let arr = data.content
            let context = `<table border="1"><tr>
                            <th>STT</th>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Salary</th>
                            <th>Branch</th>
                            <th colspan="2">Action</th>
                            </tr>`
            for (let i = 0; i < arr.length; i++) {
                context += `<tr>
                            <td>${i + 1}</td>
                            <td>${arr[i].code}</td> 
                            <td><a href="#" onclick="detail(${arr[i].id})"> ${arr[i].name}</a></td>
                            <td>${arr[i].age}</td>
                            <td>${arr[i].salary}</td>
                            <td>${arr[i].branch.name}</td>
                            <td><button onclick="updateForm(${arr[i].id})">Update</button></td>
                          
                            <td><button onclick="deleteEmployee(${arr[i].id})">Delete</button></td>
                            </tr>`
            }
            context += `</table>`
            document.getElementById("display").innerHTML = context
            $("#form").hide()
            $("#display").show()
        }
    })
}
function createForm() {
    let content = `<label><select id="branch"></label>`
    for (let i = 0; i < arr.length; i++) {
        content += `<option value="${arr[i].id}">${arr[i].name}</option>`
    }
    content += `</select>`
    document.getElementById("branchess").innerHTML = content
    $("#code").val("")
    $("#name").val("")
    $("#age").val("")
    $("#salary").val("")
    document.getElementById("title").innerHTML = "Create form"
    $("#form").show()
    document.getElementById("action").setAttribute("onclick", "createEmployee()")
    document.getElementById("action").innerHTML = "Create"
    $("#display").hide()
}

window.onload = getBranches
function getBranches() {
    $.ajax({
        url: "http://localhost:8080/employees/branches",
        type: "GET",
        success(data) {
            arr = data.content
        }
    })
}
function createEmployee(){
    let employee = {
        code: $("#code").val(),
        name: $("#name").val(),
        age: $("#age").val(),
        salary: $("#salary").val(),
        branch: {
            id: $("#branch").val()
        }
    }
    $.ajax({
        url: "http://localhost:8080/employees",
        type: "POST",
        contentType: "application/json",
        accept: "application/json",
        data: JSON.stringify(employee),
        success() {
            findAll()
        }
    })
    event.preventDefault()
}
function updateForm(id){
    let content = `<label><select id="branch"></label>`
    for (let i = 0; i < arr.length; i++) {
        content += `<option value="${arr[i].id}">${arr[i].name}</option>`
    }
    content += `</select>`
    document.getElementById("branchess").innerHTML = content
    $.ajax({
        url: `http://localhost:8080/employees/${id}`,
        type: "GET",
        success(data){
            $("#code").val(data.code)
            $("#name").val(data.name)
            $("#age").val(data.age)
            $("#salary").val(data.salary)
            document.getElementById("title").innerHTML="Update form"
            $("#form").show()
            document.getElementById("action").setAttribute("onclick",`updateEmployee(${id})`)
            document.getElementById("action").innerHTML="Update"
            $("#display").hide()
        }
    })
}
function updateEmployee(id){
    let employee = {
        id: id,
        code: $("#code").val(),
        name: $("#name").val(),
        age: $("#age").val(),
        salary: $("#salary").val(),
        branch: {
            id: $("#branch").val()
        }
    }
    $.ajax({
        url: "http://localhost:8080/employees",
        type: "POST",
        contentType: "application/json",
        accept: "application/json",
        data: JSON.stringify(employee),
        success() {
            findAll()
        }
    })
    event.preventDefault()
}
function deleteEmployee(id) {
    if (confirm("Are You Sure To Delete This Employee?")) {
        $.ajax({
            url: `http://localhost:8080/employees/${id}`,
            type: "DELETE",
            success() {
                findAll()
            }
        })
    }
}
function detail(id){
    $.ajax({
        url: "http://localhost:8080/employees/" + id,
        type: "GET",
        success(data) {
            showDetail(data)
            $("#detail").show()
        }
    })
}
function showDetail(data){
    let context = ` <h1>Employee Detail</h1> 
                  <p>EmployeeCode: ${data.code} </p><br>
                  <p>Name: ${data.name} </p><br>
                  <p>Salary: ${data.salary} </p><br>
                  <p>Age: ${data.age} </p><br>
                  <p>Branch:${data.branch.name} </p><br>
                  <button onclick="backHome()">Back</button>
                 `
    document.getElementById("detail").innerHTML = context
    $("#form").hide()
    $("#display").hide()

}

function backHome() {
    $("#form").hide()
    $("#detail").hide()
    $("#display").show()
    event.preventDefault()
}

