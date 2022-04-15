let pitchsTable = document.getElementById("storiesTable");


//checkLogin().then(sendRequest);

async function sendRequest() {
    // sending basic GET request to /pitchs
    let httpResponse = await fetch('http://localhost:8080/pitchs');

    if (httpResponse && httpResponse.status===200) {
        let responseBody = await httpResponse.json();
        showPitchs(responseBody);
    }
}

function showPitchs(pitchsArr) {
    pitchsTable.innerHTML = `<tr>
    <th>ID</th>
    <th>user Id</th>
    <th>genre ID</th>
    <th>Status ID</th>
    <th>Length ID</th>
    <th>Tenative Title</th>
    <th>complition Date</th>
    <th>Description</th>
    <th>Blurb</th>
    </tr>`;
    
    // for each pet in the pets array from the http response
    for (let pitch of pitchsArr) {
        // these pets are coming from Java so the fields are the same
        let row = document.createElement('tr');
        row.innerHTML = `
            <td>${pitch.pitch_id}</td>
            <td>${pitch.user_id}</td>
            <td>${pitch.genre_id}</td>
            <td>${pitch.status_id}</td>
            <td>${pitch.length_id}</td>
            <td>${pitch.tentative_title}</td>
            <td>${pitch.comp_date}</td>
            <td>${pitch.description}</td>
            <td>${pitch.blurb}</td>

        `;
        // add the row to the table
        pitchsTable.appendChild(row);
     
    }
    hideloginbox();

}

function  hideloginbox() {
    var x = document.getElementById("login-div");
      x.style.display = "none";
    }