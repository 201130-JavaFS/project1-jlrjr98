"use strict";

const url = 'http://localhost:8080/project-1/';

let tableRow = document.getElementById("t-row");
let tab1 = document.getElementById("tab-1");
let tab2 = document.getElementById("tab-2");
let tab3 = document.getElementById("tab-3");
let tab4 = document.getElementById("tab-4");
let tab5 = document.getElementById("tab-5");

let usernameDiv = document.getElementById("username-div");
let passwordDiv = document.getElementById("password-div");
let loginBtnDiv = document.getElementById("login-btn-div");

let loginBtn = document.getElementById("login-btn");

let mainHeader = document.getElementById("main-header");
let mainDiv = document.getElementById("main-div");
let contentDiv = document.getElementById("content-div");
let loginFormInner = document.getElementById("login-form-inner");
let divForWarning = document.getElementById("div-for-warning");

login();

loginBtn.addEventListener('click', async function () {login();});
loginBtn.addEventListener('click', async function () {addDivToWarningDiv();});

function login() {

		checkIfLoggedIn();

		async function checkIfLoggedIn() {
			let response = await fetch(url+'check-session', {
				method:'GET',
				credentials:'include'
			})
			let textData = await response.text();
			if (textData == "logged in") {
				var userN = sessionStorage.getItem("username");
				var passW = sessionStorage.getItem("password");
				
				start(userN, passW);
			}

		let username = document.getElementById("username").value;
		let password = document.getElementById("password").value;

		if (password && username) {
			start(username, password);
		} else if (divForWarning.childElementCount === 1) {
			let invalidPara = document.createElement("p");
			invalidPara.setAttribute("id", "invalid-para");
			invalidPara.innerHTML = "Invalid Username or Password";
			divForWarning.appendChild(invalidPara);
		}

		async function start(username, password) {

			let userInfo = {
				username:username,
				password:password
			}

			let response = await fetch(url+'login', {
				method:'POST',
				body:JSON.stringify(userInfo),
				credentials:'include',
				headers: {
					'Content-Type': 'application/json' 
				}
			});
		
			if (response.status === 202) {

				sessionStorage.setItem("username", username);
				sessionStorage.setItem("password", password);

				if (document.getElementById("invalid-para") != null) {
					document.getElementById("invalid-para").remove();
				}

				let textData = await response.text()
				let data = JSON.parse(textData);

				if (data["userId"] === 1) {

					tab4.innerHTML = "Review Tickets";
					tab4.id = "tab-2";
		
					let tab5 = document.createElement("th");
					tab5.setAttribute("id", "tab-5");
					tab5.innerHTML = "Sign Out";
					tab5.addEventListener('click', signout);
					tableRow.appendChild(tab5);
		
					tab4.innerHTML = "Review Tickets";
					tab4.addEventListener('click', function () {reviewTickets(data);});
				} else {
					tab4.id = "tab-5";
					tab4.innerHTML = "Sign Out";
					tab4.addEventListener('click', signout);
				}

				home(data);
				tab1.addEventListener('click', function () {home(data);});
				tab2.addEventListener('click', function () {viewTickets(data["userId"]);});
				let createdTicket = false;
				tab3.addEventListener('click', function () {createTicketPage(data, createdTicket);});
		
			} else if (divForWarning.childElementCount === 1) {
				let invalidPara = document.createElement("p");
				invalidPara.setAttribute("id", "invalid-para");
				invalidPara.innerHTML = "Invalid Username or Password";
				divForWarning.appendChild(invalidPara);
				}
			}
		}	
}

async function home(data) {

	tab1.className = "current-tab";
	tab2.className = "";
	tab3.className = "";
	tab4.className = "";

	if (tab5 != null) {
		tab5.className = "";
	}

	mainHeader.innerHTML = "Employee Information:";
	document.getElementById("content-div").remove();
	let contentDiv = document.createElement("div");
	contentDiv.setAttribute("id", "content-div");
	mainDiv.appendChild(contentDiv);

	let employeeTable = document.createElement("table");
	employeeTable.setAttribute("id", "employee-table");
	let employeeRow0 = document.createElement("tr");
	let employeeRow1 = document.createElement("tr");
	let employeeRow2 = document.createElement("tr");
	let employeeRow3 = document.createElement("tr");
	let employeeRow4 = document.createElement("tr");

	let employeeHead0 = document.createElement("th");
	let employeeCell0 = document.createElement("td");
	let employeeHead1 = document.createElement("th");
	let employeeCell1 = document.createElement("td");
	let employeeHead2 = document.createElement("th");
	let employeeCell2 = document.createElement("td");
	let employeeHead3 = document.createElement("th");
	let employeeCell3 = document.createElement("td");
	let employeeHead4 = document.createElement("th");
	let employeeCell4 = document.createElement("td");

	employeeTable.appendChild(employeeRow0);
	employeeTable.appendChild(employeeRow1);
	employeeTable.appendChild(employeeRow2);
	employeeTable.appendChild(employeeRow3);
	employeeTable.appendChild(employeeRow4);

	employeeRow0.appendChild(employeeHead0);
	employeeRow0.appendChild(employeeCell0);
	employeeRow1.appendChild(employeeHead1);
	employeeRow1.appendChild(employeeCell1);
	employeeRow2.appendChild(employeeHead2);
	employeeRow2.appendChild(employeeCell2);
	employeeRow3.appendChild(employeeHead3);
	employeeRow3.appendChild(employeeCell3);
	employeeRow4.appendChild(employeeHead4);
	employeeRow4.appendChild(employeeCell4);

	employeeHead0.innerHTML = "Employee Id: ";
	employeeHead1.innerHTML = "Username: ";
	employeeHead2.innerHTML = "Full Name: ";
	employeeHead3.innerHTML = "Email: ";
	employeeHead4.innerHTML = "Job Title: ";

	employeeCell0.innerHTML = data["userId"];
	employeeCell1.innerHTML = data["username"];
	employeeCell2.innerHTML = data["firstName"] + " " + data["lastName"];
	employeeCell3.innerHTML = data["email"];
	employeeCell4.innerHTML = data["userRole"];

	contentDiv.appendChild(employeeTable);
}

async function viewTickets(userId) {

	let response = await fetch(url+'view-tickets', {
		method:'POST',
		body:JSON.stringify(userId),
		credentials:'include'
	});

	if (response.status == 202) {

		let textData = await response.text()
		let data = JSON.parse(textData);

		tab1.className = "";
		tab2.className = "current-tab";
		tab3.className = "";
		tab4.className = "";
		if (tab5 != null) {
			tab5.className = "";
		}
		mainHeader.innerHTML = "Your Reimbursement Tickets:";
		document.getElementById("content-div").remove();
		let contentDiv = document.createElement("div");
		contentDiv.setAttribute("id", "content-div");
		mainDiv.appendChild(contentDiv);

		let ticketTable = document.createElement("table");
		ticketTable.setAttribute("id", "ticket-table");
		ticketTable.setAttribute("class", "table");

		let headRow = document.createElement("tr");
		headRow.setAttribute("id", "ticket-head-row");

		let ticketHead1 = document.createElement("th");
		let ticketHead2 = document.createElement("th");
		let ticketHead3 = document.createElement("th");
		let ticketHead4 = document.createElement("th");
		let ticketHead5 = document.createElement("th");
		let ticketHead6 = document.createElement("th");
		let ticketHead7 = document.createElement("th");
		let ticketHead8 = document.createElement("th");
		let ticketHead9 = document.createElement("th");

		headRow.appendChild(ticketHead1);
		headRow.appendChild(ticketHead2);
		headRow.appendChild(ticketHead3);
		headRow.appendChild(ticketHead4);
		headRow.appendChild(ticketHead5);
		headRow.appendChild(ticketHead6);
		headRow.appendChild(ticketHead7);
		headRow.appendChild(ticketHead8);
		headRow.appendChild(ticketHead9);

		ticketHead1.innerHTML = "Ticket Id";
		ticketHead2.innerHTML = "Amount";
		ticketHead3.innerHTML = "Date Submitted";
		ticketHead4.innerHTML = "Date Resolved";
		ticketHead5.innerHTML = "Description";
		ticketHead6.innerHTML = "Reciept";
		ticketHead7.innerHTML = "Resolver Id";
		ticketHead8.innerHTML = "Type";
		ticketHead9.innerHTML = "Status";

		let tableHead = document.createElement("thead");
		tableHead.setAttribute("class", "thead-dark");

		tableHead.appendChild(headRow);
		ticketTable.appendChild(tableHead);

		let tableBody = document.createElement("tbody");
		
		for (var i = 0; i < data.length; i++) {

			let row = document.createElement("tr");
			row.setAttribute("class", "ticket-body-row");
	  
			let cell = document.createElement("td");
			cell.innerHTML = data[i].reimbId;
			row.appendChild(cell);
	  
			let cell2 = document.createElement("td");
			cell2.innerHTML = "$" + data[i].reimbAmount.toFixed(2);
			row.appendChild(cell2);
	  
			let cell3 = document.createElement("td");
			cell3.innerHTML = data[i].reimbSubmittedString;
			row.appendChild(cell3);
	  
			let cell4 = document.createElement("td");

			if (data[i].reimbResolvedString != null) {
				cell4.innerHTML = data[i].reimbResolvedString;
			} else {
				cell4.innerHTML = "---";
			}

			row.appendChild(cell4);

			let cell5 = document.createElement("td");
			let popup = document.createElement("div");
			popup.setAttribute("class", "popup");
			popup.setAttribute("onclick", "popupFunction(" + i + ")")
			popup.setAttribute("id", "popup-btn");
			popup.innerHTML = "";
			let text = document.createElement("span");
			text.setAttribute("class", "popuptext");
			text.setAttribute("id", "myPopup" + i);
			text.innerHTML = data[i].reimbDescription;

			popup.appendChild(text);
			cell5.appendChild(popup);
			row.appendChild(cell5);

			let cell6 = document.createElement("td");

			if (data[i].reimbReciept != null) {
				cell6.innerHTML = data[i].reimbReciept;
			} else {
				cell6.innerHTML = "---";
			}

			row.appendChild(cell6);

			let cell7 = document.createElement("td");

			if (data[i].reimbResolver != null && data[i].reimbResolver != 0) {
				cell7.innerHTML = data[i].reimbResolver;	
			} else {
				cell7.innerHTML = "---";
			}

			row.appendChild(cell7);

			let cell8 = document.createElement("td");
			cell8.innerHTML = data[i].reimbType;
			row.appendChild(cell8);

			let cell9 = document.createElement("td");
			cell9.innerHTML = data[i].reimbStatus;
			row.appendChild(cell9);
			if (data[i].reimbStatus == "pending") {
				cell9.setAttribute("id", "pending");
			} else if (data[i].reimbStatus == "approved") {
				cell9.setAttribute("id", "approved");
			} else {
				cell9.setAttribute("id", "denied");
			}
			tableBody.appendChild(row);
		}
		ticketTable.appendChild(tableBody);
		contentDiv.appendChild(ticketTable);
	}
}

async function createTicketPage(data, createdTicket) {

	tab1.className = "";
	tab2.className = "";
	tab3.className = "current-tab";
	tab4.className = "";
	if (tab5 != null) {
		tab5.className = "";
	}

	if (createdTicket == true) {
		mainHeader.innerHTML = "Ticket Successfully Created";
	} else {
		mainHeader.innerHTML = "Create a New Reimbursement Ticket:";	
	}
	
	document.getElementById("content-div").remove();
	let contentDiv = document.createElement("div");
	contentDiv.setAttribute("id", "content-div");
	mainDiv.appendChild(contentDiv);

	let createTicketForm = document.createElement("div");
	createTicketForm.setAttribute("id", "create-ticket-form");

	let amountDiv = document.createElement("div");
	let amountLabel = document.createElement("p");
	let amountInput = document.createElement("input");
	amountLabel.innerHTML = "Reimbursement Amount:";
	amountInput.setAttribute("class", "form-control hover");
	amountInput.setAttribute("type", "text");
	amountInput.setAttribute("id", "reimb-amount");
	amountDiv.appendChild(amountLabel);
	amountDiv.appendChild(amountInput);

	let descriptionDiv = document.createElement("div");
	let descriptionLabel = document.createElement("p");
	let descriptionInput = document.createElement("textarea");
	descriptionLabel.innerHTML = "Description(Optional):";
	descriptionInput.setAttribute("class", "form-control hover");
	descriptionInput.setAttribute("id", "reimb-description");
	descriptionDiv.appendChild(descriptionLabel);
	descriptionDiv.appendChild(descriptionInput);

	let typeIdDiv = document.createElement("div");
	let typeIdLabel = document.createElement("p");
	typeIdDiv.appendChild(typeIdLabel);

	typeIdLabel.innerHTML = "Reimbursement Type:";

	let label1 = document.createElement("label");
	label1.innerHTML = "Lodging";
	label1.setAttribute("for", "type1");

	let typeIdInput1 = document.createElement("input");
	typeIdInput1.setAttribute("type", "radio");
	typeIdInput1.setAttribute("name", "type");
	typeIdInput1.setAttribute("id", "type1");
	typeIdInput1.setAttribute("value", "1");
	
	typeIdDiv.appendChild(typeIdInput1);
	typeIdDiv.appendChild(label1);

	let label2 = document.createElement("label");
	label2.innerHTML = "Food";
	label2.setAttribute("for", "type2");

	let typeIdInput2 = document.createElement("input");
	typeIdInput2.setAttribute("type", "radio");
	typeIdInput2.setAttribute("name", "type");
	typeIdInput2.setAttribute("id", "type2");
	typeIdInput2.setAttribute("value", "2");
	
	typeIdDiv.appendChild(typeIdInput2);
	typeIdDiv.appendChild(label2);

	let label3 = document.createElement("label");
	label3.innerHTML = "Travel";
	label3.setAttribute("for", "type3");

	let typeIdInput3 = document.createElement("input");
	typeIdInput3.setAttribute("type", "radio");
	typeIdInput3.setAttribute("name", "type");
	typeIdInput3.setAttribute("id", "type3");
	typeIdInput3.setAttribute("value", "3");

	typeIdDiv.appendChild(typeIdInput3);
	typeIdDiv.appendChild(label3);

	let label4 = document.createElement("label");
	label4.innerHTML = "Other (specify in description)";
	label4.setAttribute("for", "type4");

	let typeIdInput4 = document.createElement("input");
	typeIdInput4.setAttribute("type", "radio");
	typeIdInput4.setAttribute("name", "type");
	typeIdInput4.setAttribute("id", "type4");
	typeIdInput4.setAttribute("value", "4");

	typeIdDiv.appendChild(typeIdInput4);
	typeIdDiv.appendChild(label4);

	createTicketForm.appendChild(amountDiv);
	createTicketForm.appendChild(typeIdDiv);
	createTicketForm.appendChild(descriptionDiv);

	let createButton = document.createElement("button");
	createButton.setAttribute("class", "btn btn-primary hover");
	createButton.setAttribute("id", "create-btn");
	createButton.innerHTML = "Submit";

	contentDiv.appendChild(createTicketForm);
	contentDiv.appendChild(createButton);

	createButton.addEventListener('click', function () {createTicket(data);});}

async function createTicket(data) {

	let reimbAmount = document.getElementById("reimb-amount").value;
	let reimbDescription = document.getElementById("reimb-description").value;

	if (Number(reimbAmount) != undefined && typeof Number(reimbAmount) === 'number') {
		if (reimbDescription.length < 250) {

			let types = document.getElementsByName("type"); 
			for(var i = 0; i < types.length; i++) { 
				if(types[i].checked) {
					let reimbTypeId = types[i].value;			

					let ticketInfo = {
						userId:data["userId"],
						reimbAmount:reimbAmount,
						reimbDescription:reimbDescription,
						reimbTypeId:reimbTypeId
					};

					let response = await fetch(url+'create-ticket', {
						method:'POST',
						body:JSON.stringify(ticketInfo),
						credentials:'include'
					});

					if (response.status == 201) {
						let createdTicket = true;
						createTicketPage(data, createdTicket);
					} else {
						let failurePara = document.createElement("p");
						failurePara.innerHTML = "Error. Failed to Create Reimbursement Ticket.";
						document.getElementById("div-for-warning").append(failurePara);
					}
				}
			}

		} else {
			let failurePara = document.createElement("p");
			failurePara.innerHTML = "Error. Description must by under 250 characters.";
			document.getElementById("div-for-warning").append(failurePara);
		}

	} else {
		let failurePara = document.createElement("p");
		failurePara.innerHTML = "Error. Entered Reimbursement Amount is Invald.";
		document.getElementById("div-for-warning").append(failurePara);
	}
}

async function reviewTickets(userData) {
	let response = await fetch(url+'review-tickets',  {
		method:'POST',
		body:JSON.stringify(userData["userId"]),
		credentials:'include'
	});

	if (response.status == 200) {
		let textData = await response.text()
		let data = JSON.parse(textData);

		tab1.className = ""
		tab2.className = "";
		tab3.className = "";
		tab4.className = "current-tab";
		document.getElementById("tab-5").className = "";

		mainHeader.innerHTML = "List of Submitted Reimbursement Tickets:";
		document.getElementById("content-div").remove();

		let selectorDiv = document.createElement("div");
		let statusSelector = document.createElement("select");
		statusSelector.setAttribute("name", "status-selector");
		let allOption = document.createElement("option");
		let pendingOption = document.createElement("option");
		let approvedOption = document.createElement("option");
		let deniedOption = document.createElement("option");

		allOption.innerHTML = "All";
		allOption.setAttribute("value", "no filter");
		pendingOption.innerHTML = "pending";
		pendingOption.setAttribute("value", "pending");
		approvedOption.innerHTML = "approved";
		approvedOption.setAttribute("value", "approved");
		deniedOption.innerHTML = "denied";
		deniedOption.setAttribute("value", "denied");

		let statusSelectorBtn = document.createElement("button");
		statusSelectorBtn.innerHTML = "Filter By Status";
		statusSelectorBtn.setAttribute("id", "status-selector-btn"); 

		statusSelector.appendChild(allOption);
		statusSelector.appendChild(pendingOption);
		statusSelector.appendChild(approvedOption);
		statusSelector.appendChild(deniedOption);

		selectorDiv.appendChild(statusSelector);
		selectorDiv.appendChild(statusSelectorBtn);

		let contentDiv = document.createElement("div");
		contentDiv.setAttribute("id", "content-div");
		mainDiv.appendChild(contentDiv);

		contentDiv.appendChild(selectorDiv);

		let ticketTable = document.createElement("table");
		ticketTable.setAttribute("id", "ticket-table");
		ticketTable.setAttribute("class", "table");

		let headRow = document.createElement("tr");
		headRow.setAttribute("id", "ticket-head-row");

		let ticketHead1 = document.createElement("th");
		let ticketHead0 = document.createElement("th");
		let ticketHead2 = document.createElement("th");
		let ticketHead3 = document.createElement("th");
		let ticketHead4 = document.createElement("th");
		let ticketHead5 = document.createElement("th");
		let ticketHead6 = document.createElement("th");
		let ticketHead7 = document.createElement("th");
		let ticketHead8 = document.createElement("th");
		let ticketHead9 = document.createElement("th");
		let ticketHead10 = document.createElement("th");

		headRow.appendChild(ticketHead1);
		headRow.appendChild(ticketHead0);
		headRow.appendChild(ticketHead2);
		headRow.appendChild(ticketHead3);
		headRow.appendChild(ticketHead4);
		headRow.appendChild(ticketHead5);
		headRow.appendChild(ticketHead6);
		headRow.appendChild(ticketHead7);
		headRow.appendChild(ticketHead8);
		headRow.appendChild(ticketHead9);
		headRow.appendChild(ticketHead10);

		ticketHead1.innerHTML = "Ticket Id";
		ticketHead0.innerHTML = "Author Id";
		ticketHead2.innerHTML = "Amount";
		ticketHead3.innerHTML = "Date Submitted";
		ticketHead4.innerHTML = "Date Resolved";
		ticketHead5.innerHTML = "Description";
		ticketHead6.innerHTML = "Reciept";
		ticketHead7.innerHTML = "Resolver Id";
		ticketHead8.innerHTML = "Type";
		ticketHead9.innerHTML = "Status";
		ticketHead10.innerHTML = "Review";

		let tableHead = document.createElement("thead");
		tableHead.setAttribute("class", "thead-dark");

		tableHead.appendChild(headRow);
		ticketTable.appendChild(tableHead);

		let tableBody = document.createElement("tbody");

		statusSelectorBtn.addEventListener('click', function() {showTickets();})

		function showTickets() {

			while (tableBody.firstChild) {
				tableBody.removeChild(tableBody.lastChild);
			  }

			let selectedStatus = statusSelector[statusSelector.selectedIndex].value;

			for (var i = 0; i < data.length; i++) {

				if (data[i].reimbStatus == selectedStatus && selectedStatus != "no filter" || selectedStatus == "no filter") {

					let row = document.createElement("tr");
					row.setAttribute("class", "ticket-body-row");
			
					let cell = document.createElement("td");
					cell.innerHTML = data[i].reimbId;
					row.appendChild(cell);

					let cell0 = document.createElement("td");
					cell0.innerHTML = data[i].reimbAuthor;
					row.appendChild(cell0);
			
					let cell2 = document.createElement("td");
					cell2.innerHTML = "$" + data[i].reimbAmount.toFixed(2);
					row.appendChild(cell2);
			
					let cell3 = document.createElement("td");
					cell3.innerHTML = data[i].reimbSubmittedString;
					row.appendChild(cell3);
			
					let cell4 = document.createElement("td");

					if (data[i].reimbResolvedString != null) {
						cell4.innerHTML = data[i].reimbResolvedString;
					} else {
						cell4.innerHTML = "---";
					}

					row.appendChild(cell4);
			
					let cell5 = document.createElement("td");
					let popup = document.createElement("div");
					popup.setAttribute("class", "popup");
					popup.setAttribute("onclick", "popupFunction(" + i + ")")
					popup.setAttribute("id", "popup-btn");
					popup.innerHTML = "";
					let text = document.createElement("span");
					text.setAttribute("class", "popuptext");
					text.setAttribute("id", "myPopup" + i);
					text.innerHTML = data[i].reimbDescription;

					popup.appendChild(text);
					cell5.appendChild(popup);
					row.appendChild(cell5);
			
					let cell6 = document.createElement("td");

					if (data[i].reimbReciept != null) {
						cell6.innerHTML = data[i].reimbReciept;
					} else {
						cell6.innerHTML = "---";
					}

					row.appendChild(cell6);

					let cell7 = document.createElement("td");

					if (data[i].reimbResolver != null && data[i].reimbResolver != 0) {
						cell7.innerHTML = data[i].reimbResolver;	
					} else {
						cell7.innerHTML = "---";
					}

					row.appendChild(cell7);

					let cell8 = document.createElement("td");
					cell8.innerHTML = data[i].reimbType;
					row.appendChild(cell8);

					let cell9 = document.createElement("td");
					cell9.innerHTML = data[i].reimbStatus;
					row.appendChild(cell9);
					if (data[i].reimbStatus == "pending") {
						cell9.setAttribute("id", "pending");
					} else if (data[i].reimbStatus == "approved") {
						cell9.setAttribute("id", "approved");
					} else {
						cell9.setAttribute("id", "denied");
					}

					let cell10 = document.createElement("td");
					cell10.setAttribute("id", "cell-10");
					if (data[i].reimbStatus == "pending") {
	
						let reviewSelector = document.createElement("select");
						reviewSelector.setAttribute("name", "review-ticket");
						reviewSelector.setAttribute("id", "rev-" + i);
						let noneOption = document.createElement("option");
						let approveOption = document.createElement("option");
						let declineOption = document.createElement("option");

						noneOption.innerHTML = "---";
						approveOption.innerHTML = "APPROVE";
						approveOption.setAttribute("value", "2");
						declineOption.innerHTML = "DECLINE";
						declineOption.setAttribute("value", "3");

						reviewSelector.appendChild(noneOption);
						reviewSelector.appendChild(approveOption);
						reviewSelector.appendChild(declineOption);

						 cell10.appendChild(reviewSelector);

						let revBtn = document.createElement("button");
						revBtn.setAttribute("id", "rev-btn-" + i);
						revBtn.setAttribute('onclick', 'sendReview(' + i + ', ' + data[i].reimbId + ', ' + userData["userId"] + ')');
						revBtn.innerHTML = "Submit";

						cell10.appendChild(revBtn);
						}
					
					row.appendChild(cell10);
					tableBody.appendChild(row);

				} else {
					continue;
				}
			}
		}
		ticketTable.appendChild(tableBody);
		contentDiv.appendChild(ticketTable);
	}	
}

async function signout() {
	let response = await fetch(url+'signout', {
		method:'GET',
		credentials:'include'
	});

	if (response.status == 200) {
		location.reload();
	} else {
		let failurePara = document.createElement("p");
		failurePara.innerHTML = "Signout Error.";
		document.getElementById("div-for-warning").append(failurePara);
	}
}

function popupFunction(i) {
	var popup = document.getElementById("myPopup" + i);
	popup.classList.toggle("show");
}

async function sendReview(i, reimbId, reimbResolver) {
	let revSelector = document.getElementById("rev-" + i);
	let reimbStatusId = revSelector[revSelector.selectedIndex].value;

	let revInfo = {
		reimbId:reimbId,
		reimbStatusId:reimbStatusId,
		reimbResolver:reimbResolver
	}
	let response = await fetch(url+'send-review', {
		method:'PATCH',
		body:JSON.stringify(revInfo),
		credentials:'include'
	});

	if (response.status == 200) {
		document.getElementById("rev-" + i).remove();
		document.getElementById("rev-btn-" + i).remove();
		let message = document.createElement("p");
		message.innerHTML = "Submitted";
		document.getElementById("cell-10").appendChild(message);
		alert("Note: It may take processing time to view your changes.");
	} else {

		addDivToWarningDiv();

		if (divForWarning.childElementCount === 1) {
			let invalidPara = document.createElement("p");
			invalidPara.setAttribute("id", "invalid-para");
			invalidPara.innerHTML = "Error. Failed to Update Reimbursement Ticket.";
			divForWarning.append(invalidPara);
		}
	}
}

function addDivToWarningDiv() {
	let divForWarningDiv = document.createElement("div");
	divForWarning.appendChild(divForWarningDiv);
}