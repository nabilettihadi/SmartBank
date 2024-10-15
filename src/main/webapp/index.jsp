<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Simulation Crédit Bancaire</title>
    <link rel="stylesheet" href="css/styles.css" />
</head>
<body>
<div class="container">
    <div class="form-container">
        <div class="step-nav">
            <div class="step active" id="step1">1 <br> Simuler mon crédit</div>
            <div class="step" id="step2">2 <br>Mes coordonnées</div>
            <div class="step" id="step3">3 <br>Mes infos personnelles</div>
            <div class="triangle"></div>
        </div>
        <div class="step-content">
            <form id="loan-form" action="${pageContext.request.contextPath}/creditRequest" method="post">
                <div id="step-1">
                    <input type="hidden" name="step" value="step1" />
                    <label for="project">Mon projet</label>
                    <select id="project" name="project">
                        <option value="J'ai besoin d'argent">J'ai besoin d'argent</option>
                        <option value="Je finance mon véhicule d’occasion">Je finance mon véhicule d’occasion</option>
                        <option value="Je Gère mes imprévus">Je Gère mes imprévus</option>
                        <option value="Je finance mon véhicule neuf">Je finance mon véhicule neuf</option>
                        <option value="J’équipe ma maison">J’équipe ma maison</option>
                    </select>

                    <label for="profession">Je suis</label>
                    <select id="profession" name="profession">
                        <option value="Salarié du secteur privé">Salarié du secteur privé</option>
                        <option value="Fonctionnaire">Fonctionnaire</option>
                        <option value="Profession libérale">Profession libérale</option>
                        <option value="Commerçant">Commerçant</option>
                        <option value="Artisan">Artisan</option>
                        <option value="Retraité">Retraité</option>
                        <option value="Autres professions">Autres professions</option>
                    </select>

                    <div class="input-group">
                        <label for="amount">Montant (en DH)</label>
                        <div class="value-box" id="amount-value">10000</div>
                        <input type="range" id="amount" name="amount" min="5000" max="600000" value="10000" step="1000" />
                    </div>
                    <div class="input-group">
                        <label for="duration">Durée (en mois)</label>
                        <div class="value-box" id="duration-value">24</div>
                        <input type="range" id="duration" name="duration" min="12" max="120" value="24" step="6" />
                    </div>

                    <div class="input-group">
                        <label for="monthly">Mensualités (en DH)</label>
                        <div class="value-box" id="monthly-value">469</div>
                        <input type="range" id="monthly" name="monthly" min="100" max="10000" value="469" step="50" />
                    </div>
                    <button type="button" class="next-btn">
                        <b>Continuer</b> <br />
                        Sans engagement
                    </button>
                </div>
                <div class="step-content">
                    <div id="step-2" style="display: none">
                        <input type="hidden" name="step" value="step2" />
                        <label for="email">Email*</label>
                        <input type="email" id="email" name="email" required placeholder="Entrez votre email" />

                        <label for="phone">Téléphone mobile*</label>
                        <input type="tel" id="phone" name="phone" required placeholder="Entrez votre numéro de téléphone" />

                        <button type="button" class="next-btn">
                            <b>Continuer</b> <br />
                            Sans engagement
                        </button>
                    </div>
                </div>
                <div class="step-content">
                    <div id="step-3" style="display: none">
                        <input type="hidden" name="step" value="step3" />
                        <div class="form-group">
                            <label>Civilité*</label>
                            <div class="radio-group">
                                <input type="radio" id="civilite-madame" name="civilite" value="madame" required />
                                <label for="civilite-madame">Madame</label>

                                <input type="radio" id="civilite-mademoiselle" name="civilite" value="mademoiselle" required />
                                <label for="civilite-mademoiselle">Mademoiselle</label>

                                <input type="radio" id="civilite-monsieur" name="civilite" value="monsieur" required />
                                <label for="civilite-monsieur">Monsieur</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="nom">Nom*</label>
                            <input type="text" id="nom" name="nom" required placeholder="Entrez votre nom" />
                        </div>

                        <div class="form-group">
                            <label for="prenom">Prénom*</label>
                            <input type="text" id="prenom" name="prenom" required placeholder="Entrez votre prénom" />
                        </div>

                        <div class="form-group">
                            <label for="cin">Numéro CIN / Carte de séjour*</label>
                            <input type="text" id="cin" name="cin" required placeholder="Entrez votre CIN" />
                        </div>

                        <div class="form-group">
                            <label for="date-naissance">Date de naissance*</label>
                            <input type="date" id="date-naissance" name="date-naissance" required />
                        </div>

                        <div class="form-group">
                            <label for="date-embauche">Date d'embauche/début de l'activité*</label>
                            <input type="date" id="date-embauche" name="date-embauche" required />
                        </div>

                        <div class="form-group">
                            <label for="revenus">Total revenus mensuels (net en DH)*</label>
                            <input type="number" id="revenus" name="revenus" required placeholder="Entrez vos revenus" />
                        </div>

                        <div class="form-group">
                            <label>Avez-vous des crédits en cours ?</label>
                            <div class="radio-group">
                                <input type="radio" id="credit-oui" name="credit" value="oui" required />
                                <label for="credit-oui">Oui</label>
                                <input type="radio" id="credit-non" name="credit" value="non" required />
                                <label for="credit-non">Non</label>
                            </div>
                        </div>

                        <div class="button-group">
                            <button type="submit" class="submit-btn"><b>Demander ce Crédit</b></button>
                            <button type="submit" class="next-btn"><b>Voir Mon Récapitulatif</b></button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="recap-container" id="recap-container">
        <h2>Mon récapitulatif</h2>
        <div class="recap-content" id="recap-content">
            <h3>Mon projet</h3>
            <p id="recap-project">Prêt Personnel</p>
            <h3 id="recap-coord-title" style="display: none;">Coordonnées et infos personnelles</h3>
            <p id="recap-coord"></p>
            <h3 id="recap-credit-title" style="display: none;">Détails de mon crédit</h3>
            <p id="recap-credit"></p>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>