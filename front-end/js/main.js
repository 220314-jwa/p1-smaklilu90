//main functions 

function addPitch() {
    document.getElementById("login-div").innerHTML = `  <form id="pitch-form">
    <label for="country">Chose your Genre</label>
    <br>
     <select id="genre_id" name="genre">
       <option value=1>Comic Book</option>
       <option value=2>Detective</option>
       <option value=3>Fantacy</option>
       <option value=4>Historical Fiction</option>
       <option value=5>Horror</option>
       <option value=6>Literary Fiction</option>
       <option value=7>Other</option>   
     </select>
     <br>
     <label>Length Type</label>
     <br>
     <select id="length_id" name="length">
     <option value=1>Flash Fiction</option>
     <option value=2>Short Story</option>
     <option value=3>Novella</option>
     <option value=4>Novel</option>
     
   </select>
     <br>
     <label>Tentative Title</label>
     <br>
     <input type="text" id="tentative_title" name="title" size="50" placeholder="your tentative title">
     <br>

     <label>Commplition Date</label>
     <br>
     <input type="date" id="comp_date" name="birthday">
     <br>
     <label>Description</label>
     <br>
     <input type="text" id="description" name="password" size="50" placeholder="password..">
     <br>
     <label>Blurb</label>
     <br>
     <textarea id="blurb" name="Text1" cols="40" rows="3"></textarea>
      <br>
     <input type="submit" onclick="RegisterPitch()" value="Submit">
    
   </form> `;
}




function registerForm() {
    document.getElementById("addpitchhere").innerHTML = `  <form id="register-form">
    <label for="country">Chose your Role</label>
    <br>
     <select id="role_id" name="country">
       <option value=1>Author</option>
       <option value=2>Editor</option>
       <option value=3>Senior Editor</option>
     </select>
     <br>
     <label>Full Name</label>
     <br>
     <input type="text" id="full_name" name="fullName" size="50" placeholder="Full name..">
     <br>
     <label>Email</label>
     <br>
     <input type="text" id="em" name="email" size="50" placeholder="your email is User Name..">
     <br>
     <label>Password</label>
     <br>
     <input type="password" id="pass" name="password" size="50" placeholder="password..">
     <br>
     <label>Phone</label>
     <br>
     <input type="text" id="phone" name="phone" size="50" placeholder="phone(optional)..">
     <input type="submit" onclick="RegisterPitch" value="Submit">
     <h2 id="Rsuccess"> </h2>
   </form> `;
}

function homeLogin() {
    document.getElementById("login-div").innerHTML = `<form >
   
    <input type="text" id="email" name="email" placeholder="Email..">

    <input type="password" id="password" name="password" placeholder="Password..">

   
    <input type="submit" onclick="logIn()" value="Submit">
    <br>
    <label> <span style="background-color: rgb(109, 193, 189);"> New User? please <b> Register </b> using the above link! <span> </label>
  </form>
  
  `;
}

// main functions start here 

// login/logout functions 
var loggedInUser;



async function logIn() {
    let credentials = {
        email:document.getElementById('email').value,
        password:document.getElementById('password').value
    };
    let credentialJSON = JSON.stringify(credentials);

    let httpResp = await fetch('http://localhost:8080/auth',
        {method:'POST', body:credentialJSON});
    if (httpResp && httpResp.status === 200) {
        loggedInUser = await httpResp.json();
        sessionStorage.setItem('Auth-Token', loggedInUser.id);
       // await checkLogin();
        loggedInAction();
      
    
    } 

}


  



function  loggedInAction(){
   let roleId = loggedInUser.role_id;
   let email = loggedInUser.email;
   document.getElementById('nameDisplay').innerText = email;
  // document.getElementById('showP').innerText = "Show Stories";

 
   sendRequest();
   showPitchs();
   
    
}

function logOut() {
    sessionStorage.removeItem('Auth-Token');
    loggedInUser=null;
    document.getElementById('nameDisplay').innerText = " ";
    document.getElementById('storiesTable').innerText = " ";
    showloginbox();
}

function  showloginbox() {
    var x = document.getElementById("login-div");
      x.style.display = "block";
    }

async function Register() {
    let credentials = {
        role_id:document.getElementById('role_id').value,
        full_name:document.getElementById('full_name').value,
        email:document.getElementById('em').value,
        password:document.getElementById('pass').value,
        phone:document.getElementById('phone').value
    };
    let credentialJSON = JSON.stringify(credentials);

    let httpResp = await fetch('http://localhost:8080/users',
        {method:'POST', body:credentialJSON});
    if (httpResp && httpResp.status === 200) {
       loggedInUser = await httpResp.json();
     //   sessionStorage.setItem('Auth-Token', loggedInUser.id);
       // await checkLogin();
      
      
    
    } 
}

    async function  RegisterPitch(){

        let credentials = {
            user_id:loggedInUser.user_id,
            genre_id:document.getElementById('genre_id').value,
            length_id:document.getElementById('length_id').value,
            tentative_title:document.getElementById('tentative_title').value,
            comp_date:document.getElementById('comp_date').value,
            description:document.getElementById('description').value,
            blurb:document.getElementById('blurb').value
          
        };
        let credentialJSON = JSON.stringify(credentials);
    
        let httpResp = await fetch('http://localhost:8080/pitchs',
            {method:'POST', body:credentialJSON});
        if (httpResp && httpResp.status === 200) {
           loggedInUser = await httpResp.json();
         //   sessionStorage.setItem('Auth-Token', loggedInUser.id);
           // await checkLogin();
          
        }
    }






