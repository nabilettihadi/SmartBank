document.addEventListener("DOMContentLoaded", function () {
    const steps = document.querySelectorAll(".step");
    const step1 = document.getElementById("step-1");
    const step2 = document.getElementById("step-2");
    const step3 = document.getElementById("step-3");
    const nextButtons = document.querySelectorAll(".next-btn");
    const amountInput = document.getElementById("amount");
    const amountValue = document.getElementById("amount-value");
    const durationInput = document.getElementById("duration");
    const durationValue = document.getElementById("duration-value");
    const monthlyInput = document.getElementById("monthly");
    const monthlyValue = document.getElementById("monthly-value");

    let currentStep = 1;
    const interestRate = 0.04;

    function calculateMonthlyPayment(amount, duration) {
        const monthlyRate = interestRate / 12;
        return Math.round((amount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -duration)));
    }

    function updateMonthly() {
        const amount = parseInt(amountInput.value);
        const duration = parseInt(durationInput.value);
        const monthly = calculateMonthlyPayment(amount, duration);
        monthlyInput.value = monthly;
        monthlyValue.textContent = monthly;
    }

    function updateStep() {
        step1.style.display = "none";
        step2.style.display = "none";
        step3.style.display = "none";

        if (currentStep === 1) {
            step1.style.display = "block";
        } else if (currentStep === 2) {
            step2.style.display = "block";
        } else if (currentStep === 3) {
            step3.style.display = "block";
        }

        steps.forEach((step, index) => {
            step.classList.toggle("active", index + 1 === currentStep);
        });
    }

    amountInput.addEventListener("input", function () {
        amountValue.textContent = amountInput.value;
        updateMonthly();
    });

    durationInput.addEventListener("input", function () {
        durationValue.textContent = durationInput.value;
        updateMonthly();
    });

    function updateRecap() {
        const project = document.getElementById("project").value;
        const profession = document.getElementById("profession").value;
        const amount = amountInput.value;
        const duration = durationInput.value;
        const monthly = monthlyInput.value;
        const email = document.getElementById("email").value || "nabil@gmail.com";
        const phone = document.getElementById("phone").value || "0610203040";
        const nom = document.getElementById("nom").value || "nassif";
        const prenom = document.getElementById("prenom").value || "mohammed";

        if (currentStep >=1) {
            document.getElementById("recap-project").textContent = project;
            document.getElementById("recap-credit-title").style.display = "block";
            document.getElementById("recap-credit").innerHTML = `
                Vous êtes: ${profession}<br>
                Montant: ${amount} DH<br>
                Durée: ${duration} mois<br>
                Mensualité: ${monthly} DH<br>
                Frais de dossier: 271,50 DH
            `;
        }
        if (currentStep >= 2) {
            document.getElementById("recap-coord-title").style.display = "block";
            document.getElementById("recap-coord").innerHTML = `
                Nom: ${nom}<br>
                Prénom: ${prenom}<br>
                Email: ${email}<br>
                Téléphone: ${phone}
            `;
        }
    }

    nextButtons.forEach((button, index) => {
        button.addEventListener("click", function () {
            let validationFunction;
            if (index === 0) {
                validationFunction = validateStep1;
            } else if (index === 1) {
                validationFunction = validateStep2;
            }

            if (validationFunction && validationFunction()) {
                updateRecap();
                currentStep++;
                updateStep();
            }
        });
    });

    document.querySelector(".submit-btn").addEventListener("click", function (event) {
        if (!validateStep3()) {
            event.preventDefault();
        }
    });

    function validateStep1() {
        const project = document.getElementById("project").value;
        const profession = document.getElementById("profession").value;
        const amount = parseInt(amountInput.value);
        const duration = parseInt(durationInput.value);

        if (!project || !profession || !amount || !duration) {
            alert("Veuillez sélectionner tous les champs obligatoires.");
            return false;
        }

        return true;
    }

    function validateStep2() {
        const email = document.getElementById("email").value;
        const phone = document.getElementById("phone").value;
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const phoneRegex = /^\d{10}$/;

        if (!email || !phone) {
            alert("Veuillez remplir tous les champs obligatoires.");
            return false;
        }

        if (!emailRegex.test(email)) {
            alert("Veuillez entrer un email valide.");
            return false;
        }

        if (!phoneRegex.test(phone)) {
            alert("Veuillez entrer un numéro de téléphone valide.");
            return false;
        }
        return true;
    }

    function validateStep3() {
        const nom = document.getElementById("nom").value;
        const prenom = document.getElementById("prenom").value;
        const cin = document.getElementById("cin").value;
        const dateNaissance = document.getElementById("date-naissance").value;
        const dateEmbauche = document.getElementById("date-embauche").value;
        const revenus = document.getElementById("revenus").value;

        if (!nom || !prenom || !cin || !dateNaissance || !dateEmbauche || !revenus) {
            alert("Veuillez remplir tous les champs obligatoires.");
            return false;
        }

        if (new Date(dateNaissance) >= new Date(dateEmbauche)) {
            alert("La date de naissance doit être inférieure à la date d'embauche.");
            return false;
        }

        return true;
    }

    updateStep();
});