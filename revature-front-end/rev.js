"use strict";

const url = 'http://localhost:8080/project-1/';

//DOM
let tableRow = document.getElementById("t-row");
let tab1 = document.getElementById("tab-1");
let tab2 = document.getElementById("tab-2");
let tab3 = document.getElementById("tab-3");
let tab4 = document.getElementById("tab-4");

let usernameDiv = document.getElementById("username-div");
let passwordDiv = document.getElementById("password-div");
let loginBtnDiv = document.getElementById("login-btn-div");

let loginBtn = document.getElementById("login-btn");

let mainHeader = document.getElementById("main-header");
let mainDiv = document.getElementById("main-div");
let contentDiv = document.getElementById("content-div");
let loginFormInner = document.getElementById("login-form-inner");
let divForWarning = document.getElementById("div-for-warning");


loginBtn.addEventListener('click', async function() {

	let unhashedPassword = document.getElementById("password").value;
	let hashed = sha256(unhashedPassword);

	hashed.then(function(result) {

		console.log(result);

		start();

		async function start() {

			let username = document.getElementById("username").value;
			let password = result;

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

			let textData = await response.text()
			console.log(textData);
			let data = JSON.parse(textData);

			if (response.status === 202) {

				if (data["userId"] === 1) {

					tab4.innerHTML = "Review Tickets";
					tab4.id = "tab-2";
		
					let tab5 = document.createElement("th");
					tab5.setAttribute("id", "tab-5");
					tab5.innerHTML = "Sign Out";
					tableRow.appendChild(tab5);
		
					tab4.innerHTML = "Review Tickets";
					tab4.addEventListener('click', reviewTickets);
				} else {
					tab4.id = "tab-5";
					tab4.innerHTML = "Sign Out";
				}

				home(data);
				tab1.addEventListener('click', home);
				tab2.addEventListener('click', viewTickets);
				tab3.addEventListener('click', createTicketPage);
		
			} else if (divForWarning.childElementCount === 0) {
				let invalidPara = getInvalidPara();
				divForWarning.appendChild(invalidPara);
			}

		}
		
	});		
});



async function home(data) {
	
	// let response = await fetch(url+'home', {
	// 	method:'POST',
	// 	body:JSON.stringify(username),
	// 	credentials:'include'
	// });

	// let textData = await response.text()
	// let data = JSON.parse(textData);

	if (!(mainHeader.innerHTML == "Employee Information:")) {
		tab2.className = "";
		tab3.className = "";
		tab4.className = "";
		tab1.className = "current-tab";

		mainHeader.innerHTML = "Employee Information:";
		whipeMain();
		let contentDiv = document.createElement("div");
		contentDiv.setAttribute("id", "content-div");
		mainDiv.appendChild(contentDiv);

		let employeeTable = document.createElement("table");
		employeeTable.setAttribute("id", "employee-table");
		let employeeRow1 = document.createElement("tr")
		let employeeRow2 = document.createElement("tr")
		let employeeRow3 = document.createElement("tr")
		let employeeRow4 = document.createElement("tr")

		let employeeHead1 = document.createElement("th");
		let employeeCell1 = document.createElement("td");
		let employeeHead2 = document.createElement("th");
		let employeeCell2 = document.createElement("td");
		let employeeHead3 = document.createElement("th");
		let employeeCell3 = document.createElement("td");
		let employeeHead4 = document.createElement("th");
		let employeeCell4 = document.createElement("td");

		employeeTable.appendChild(employeeRow1);
		employeeTable.appendChild(employeeRow2);
		employeeTable.appendChild(employeeRow3);
		employeeTable.appendChild(employeeRow4);

		employeeRow1.appendChild(employeeHead1);
		employeeRow1.appendChild(employeeCell1);
		employeeRow2.appendChild(employeeHead2);
		employeeRow2.appendChild(employeeCell2);
		employeeRow3.appendChild(employeeHead3);
		employeeRow3.appendChild(employeeCell3);
		employeeRow4.appendChild(employeeHead4);
		employeeRow4.appendChild(employeeCell4);

		employeeHead1.innerHTML = "Username: ";
		employeeHead2.innerHTML = "Full Name: ";
		employeeHead3.innerHTML = "Email: ";
		employeeHead4.innerHTML = "Job Title: ";

		employeeCell1.innerHTML = data["userName"];
		employeeCell2.innerHTML = data["firstName"] + " " + data["lastName"];
		employeeCell3.innerHTML = data["email"];
		employeeCell4.innerHTML = data["userRole"];

		contentDiv.appendChild(employeeTable);

		// if (data["userId"] === 1) {

		// 	tab4.innerHTML = "Review Tickets";
		// 	tab4.id = "tab-2";

		// 	let tab5 = document.createElement("th");
		// 	tab5.setAttribute("id", "tab-5");
		// 	tab5.innerHTML = "Sign Out";
		// 	tableRow.appendChild(tab5);

		// 	tab4.innerHTML = "Review Tickets";
		// 	tab4.addEventListener('click', reviewTickets);
		// } else {
		// 	tab4.id = "signout-tab";
		// 	tab4.innerHTML = "Sign Out";
		// }

		// tab2.addEventListener('click', viewTickets(data));
	}
}

function createTicketPage(data) {
	
	//console.log(data);

	//make the create ticket page
	mainHeader.innerHTML = "Create a New Reimbursement Ticket:";
	whipeMain();
	let contentDiv = document.createElement("div");
	contentDiv.setAttribute("id", "content-div");
	mainDiv.appendChild(contentDiv);

	let createTicketForm = document.createElement("div");

	let amountDiv = document.createElement("div");
	let amountLabel = document.createElement("p");
	let amountInput = document.createElement("input");
	amountLabel.innerHTML = "Reimbursement Amount:";
	amountInput.setAttribute("class", "form-control hover");
	amountInput.setAttribute("type", "text");
	amountInput.setAttribute("id", "amount");
	amountDiv.appendChild(amountLabel);
	amountDiv.appendChild(amountInput);

	let descriptionDiv = document.createElement("div");
	let descriptionLabel = document.createElement("p");
	let descriptionInput = document.createElement("textarea");
	descriptionLabel.innerHTML = "Description:";
	descriptionInput.setAttribute("class", "form-control hover");
	descriptionInput.setAttribute("id", "description");
	descriptionDiv.appendChild(descriptionLabel);
	descriptionDiv.appendChild(descriptionInput);

	let typeIdDiv = document.createElement("div");
	let typeIdLabel = document.createElement("p");

	typeIdLabel.innerHTML = "Reimbursement Type:";

	let label1 = document.createElement("label");
	label1.innerHTML = "Travel";
	label1.setAttribute("for", "type1");

	let typeIdInput1 = document.createElement("input");
	typeIdInput1.setAttribute("type", "radio");
	typeIdInput1.setAttribute("name", "type");
	typeIdInput1.setAttribute("id", "type1");
	typeIdInput1.setAttribute("value", "1");
	typeIdInput1.innerHTML = "Travel";

	typeIdDiv.appendChild(label1);
	typeIdDiv.appendChild(typeIdInput1);

	let label2 = document.createElement("label");
	label2.innerHTML = "Relocation";
	label2.setAttribute("for", "type2");

	let typeIdInput2 = document.createElement("input");
	typeIdInput2.setAttribute("type", "radio");
	typeIdInput2.setAttribute("name", "type");
	typeIdInput2.setAttribute("id", "type2");
	typeIdInput2.setAttribute("value", "2");
	typeIdInput2.innerHTML = "Relocation";

	typeIdDiv.appendChild(label2);
	typeIdDiv.appendChild(typeIdInput2);

	let label3 = document.createElement("label");
	label3.innerHTML = "Medical";
	label3.setAttribute("for", "type3");

	let typeIdInput3 = document.createElement("input");
	typeIdInput3.setAttribute("type", "radio");
	typeIdInput3.setAttribute("name", "type");
	typeIdInput3.setAttribute("id", "type3");
	typeIdInput3.setAttribute("value", "3");
	typeIdInput3.innerHTML = "Medical";

	typeIdDiv.appendChild(label3);
	typeIdDiv.appendChild(typeIdInput3);

	let label4 = document.createElement("label");
	label4.innerHTML = "Business Expense";
	label4.setAttribute("for", "type4");

	let typeIdInput4 = document.createElement("input");
	typeIdInput4.setAttribute("type", "radio");
	typeIdInput4.setAttribute("name", "type");
	typeIdInput4.setAttribute("id", "type4");
	typeIdInput4.setAttribute("value", "4");
	typeIdInput4.innerHTML = "Business Expense";

	typeIdDiv.appendChild(label4);
	typeIdDiv.appendChild(typeIdInput4);

	let label5 = document.createElement("label");
	label5.innerHTML = "Other (specify in description)";
	label5.setAttribute("for", "type5");

	let typeIdInput5 = document.createElement("input");
	typeIdInput5.setAttribute("type", "radio");
	typeIdInput5.setAttribute("name", "type");
	typeIdInput5.setAttribute("id", "type5");
	typeIdInput5.setAttribute("value", "5");
	typeIdInput5.innerHTML = "Other (specifiy in description)";

	typeIdDiv.appendChild(label5);
	typeIdDiv.appendChild(typeIdInput5);

	createTicketForm.appendChild(amountDiv);
	createTicketForm.appendChild(descriptionDiv);
	createTicketForm.appendChild(typeIdDiv);


	let createButton = document.createElement("button");
	createButton.setAttribute("class", "btn btn-primary hover");
	createButton.innerHTML = "Submit";

	contentDiv.appendChild(createTicketForm);
	contentDiv.appendChild(createButton);


	createButton.addEventListener('click', createTicket);

}

async function createTicket(data) {

	let reimbAmount = document.getElementById("reimb-amount").value;
	let reimbDescription = document.getElementById("reimb-description").value;

	let reimbType = function getType() {
		let types = document.getElementsByName("type"); 
		for(i = 0; i < types.length; i++) { 
			if(types[i].checked) {
				return types[i].value;
			}
		}
	}

	let ticketInfo = {
		userId:data["userId"],
		reimbAmount:reimbAmount,
		reimbDescription:reimbDescription,
		reimbType:reimbType
	};

	let response = await fetch(url+'create-ticket', {
		method:'POST',
		body:JSON.stringify(ticketInfo),
		credentials:'unclude'
	});

	if (response.status == 201) {
		mainHeader.innerHTML = "Reimbursement Ticket Successfully Created"
	} else {
		mainHeader.innerHTML = "Failed to Create Reimbursement Ticket"
	}
}


async function reviewTickets() {
	let response = await fetch(url+'review-tickets',  {
		method:'GET',
		credentials:'include'
	});

	if (response.status == 200) {
		let textData = await response.text()
		let data = JSON.parse(textData);

		tab1.className = ""
		tab2.className = "";
		tab3.className = "";
		tab4.className = "current-tab";
		tab5.className = "";



	}
}

async function viewTickets(userId) {
	let response = await fetch(url+'view-tickets', {
		method:'POST',
		body:JSON.stringify(userId),
		credentials:'include'
	});

	if (response.status == 200) {
		let textData = await response.text()
		let data = JSON.parse(textData);

		mainHeader.innerHTML = "Your Reimbursement Tickets:";
		whipeMain();
		let contentDiv = document.createElement("div");
		contentDiv.setAttribute("id", "content-div");
		mainDiv.appendChild(contentDiv);

		let ticketTable = document.createElement("table");
		ticketTable.setAttribute("id", "ticket-table");

		let headRow = document.createElement("tr");

		let ticketHead1 = document.createElement("th");
		let ticketHead2 = document.createElement("th");
		let ticketHead3 = document.createElement("th");
		let ticketHead4 = document.createElement("th");
		let ticketHead5 = document.createElement("th");
		let ticketHead6 = document.createElement("th");
		let ticketHead7 = document.createElement("th");
		let ticketHead8 = document.createElement("th");
		let ticketHead9 = document.createElement("th");
		// let ticketHead10 = document.createElement("th");

		headRow.appendChild(ticketHead1);
		headRow.appendChild(ticketHead2);
		headRow.appendChild(ticketHead3);
		headRow.appendChild(ticketHead4);
		headRow.appendChild(ticketHead5);
		headRow.appendChild(ticketHead6);
		headRow.appendChild(ticketHead7);
		headRow.appendChild(ticketHead8);
		headRow.appendChild(ticketHead9);
		// headRow.appendChild(ticketHead10);

		ticketHead1.innerHTML = "Reimbusement Id ";
		ticketHead2.innerHTML = "Reimbursement Amount";
		ticketHead3.innerHTML = "Date Submitted";
		ticketHead4.innerHTML = "Date Resolved";
		ticketHead5.innerHTML = "Reimbursement Description";
		ticketHead6.innerHTML = "Reciept";
		ticketHead7.innerHTML = "Resolver Id";
		ticketHead8.innerHTML = "Reimbursement Type";
		ticketHead9.innerHTML = "Status";
		
		let row = document.createElement("tr");
		
		for (let ticket in data) {
	  
			let cell = document.createElement("td");
			cell.innerHTML = ticket["reimb_id"];
			row.appendChild(cell);
	  
			let cell2 = document.createElement("td");
			cell2.innerHTML = ticket["reimb_amount"];
			row.appendChild(cell2);
	  
			let cell3 = document.createElement("td");
			cell3.innerHTML = ticket["reimb_submitted"];
			row.appendChild(cell3);
	  
			let cell4 = document.createElement("td");

			if (ticket["reimb_resolved"] != null) {
				cell4.innerHTML = ticket["reimb_resolved"];
			}

			row.appendChild(cell4);
	  
			let cell5 = document.createElement("td");
			cell5.innerHTML = ticket["reimb_description"];
			row.appendChild(cell5);
	  
			let cell6 = document.createElement("td");

			if (ticket["reimb_reciept"] != null) {
				cell6.innerHTML = ticket["reimb_reciept"];
			}

			row.appendChild(cell6);

			let cell7 = document.createElement("td");

			if (ticket["reimb_resolver"] != null) {
				cell7.innerHTML = ticket["reimb_resolver"];	
			}

			row.appendChild(cell7);

			let cell8 = document.createElement("td");
			cell8.innerHTML = ticket["reimb_type"];
			row.appendChild(cell8);

			let cell9 = document.createElement("td");
			cell9.innerHTML = ticket["reimb_status"];
			row.appendChild(cell9);
		}

		contentDiv.appendChild(ticketTable);


	}
}

//Minor Functions
async function sha256(str) {
	const buf = await crypto.subtle.digest("SHA-256", new TextEncoder("utf-8").encode(str));
	return Array.prototype.map.call(new Uint8Array(buf), x=>(('00'+x.toString(16)).slice(-2))).join('');
  }

// function mustLoginFirst() {
// 	mainHeader.innerHTML = "You Must First Login:"
// }

function whipeMain() {
	// loginFormInner.remove();
	// loginBtnDiv.remove();
	document.getElementById("content-div").remove();
}

function toLoginPage() {
	window.location.replace("default.html");
}

// function createEmailInput(signupFormInner) {

// 	let emailDiv = document.createElement("div");
// 	signupFormInner.appendChild(emailDiv);

// 	let emailP = document.createElement("p");

// 	emailDiv.appendChild(emailP);

// 	let emailLabel = document.createElement("label");
// 	emailLabel.setAttribute("style", "margin-top:10px")
// 	emailLabel.setAttribute("for", "email");
// 	emailLabel.innerHTML = "Email:"

// 	emailP.appendChild(emailLabel);

// 	let emailInput = document.createElement("input");
// 	emailInput.setAttribute("type", "text");
// 	emailInput.setAttribute("id", "email");

// 	emailDiv.appendChild(emailInput);
// }

function getInvalidPara() {
	let invalidPara = document.createElement("p");
	let invalidNode = document.createTextNode("Invalid username or password");
	invalidPara.append(invalidNode);
	return invalidPara;
}