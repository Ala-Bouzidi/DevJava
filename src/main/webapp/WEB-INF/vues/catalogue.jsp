<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>StockMaster Pro - Catalogue</title>
    <style>
        /* ========== RESET & BASE ========== */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #ffffff 0%, #ffffff 100%);
            min-height: 100vh;
            padding: 20px;
        }

        /* ========== CONTENEUR PRINCIPAL ========== */
        .container {
            max-width: 1200px;
            margin: 0 auto;
            background: white;
            border-radius: 15px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.1);
            overflow: hidden;
            animation: slideIn 0.5s ease;
        }

        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateY(-30px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        /* ========== EN-TÊTE AVEC UTILISATEUR ========== */
        .header {
            background: linear-gradient(135deg, #4CAF50, #45a049);
            color: white;
            padding: 25px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            flex-wrap: wrap;
        }

        .header h1 {
            font-size: 28px;
            font-weight: 600;
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .header h1:before {
            content: "📦";
            font-size: 32px;
        }

        .user-info {
            display: flex;
            align-items: center;
            gap: 20px;
            background: rgba(255,255,255,0.2);
            padding: 10px 20px;
            border-radius: 50px;
            backdrop-filter: blur(5px);
        }

        .welcome {
            font-size: 16px;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .username {
            font-weight: bold;
            color: #ffd700;
            font-size: 18px;
            text-shadow: 1px 1px 2px rgba(0,0,0,0.2);
        }

        .logout-btn {
            background: rgba(244, 67, 54, 0.9);
            color: white;
            padding: 8px 20px;
            text-decoration: none;
            border-radius: 25px;
            font-size: 14px;
            font-weight: 600;
            transition: all 0.3s ease;
            display: flex;
            align-items: center;
            gap: 5px;
            border: 2px solid transparent;
        }

        .logout-btn:hover {
            background: #f44336;
            transform: scale(1.05);
            border-color: white;
        }

        .logout-btn:before {
            content: "🚪";
            font-size: 16px;
        }

        /* ========== SECTION CONTENU ========== */
        .content {
            padding: 30px;
        }

        .section-title {
            font-size: 24px;
            color: #333;
            margin-bottom: 25px;
            display: flex;
            align-items: center;
            gap: 10px;
            padding-bottom: 10px;
            border-bottom: 3px solid #4CAF50;
        }

        .section-title:before {
            content: "📋";
            font-size: 28px;
        }

        /* ========== TABLEAU ========== */
        .table-container {
            overflow-x: auto;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.08);
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
            font-size: 16px;
        }

        th {
            background: linear-gradient(135deg, #4CAF50, #45a049);
            color: white;
            font-weight: 600;
            padding: 15px;
            text-align: left;
            font-size: 16px;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        td {
            padding: 15px;
            border-bottom: 1px solid #eee;
            color: #555;
        }

        tr {
            transition: all 0.2s ease;
        }

        tbody tr:hover {
            background-color: #f9f9f9;
            transform: scale(1.01);
            box-shadow: 0 2px 8px rgba(76, 175, 80, 0.1);
        }

        /* Style spécial pour les colonnes */
        td:first-child {
            font-weight: bold;
            color: #4CAF50;
        }

        td:last-child {
            font-weight: 600;
            color: #333;
        }

        td:last-child:after {
            content: " €";
            color: #666;
            font-weight: normal;
        }

        /* ========== MESSAGE AUCUN PRODUIT ========== */
        .empty-message {
            text-align: center;
            padding: 50px;
            background: #f9f9f9;
            border-radius: 10px;
            color: #999;
            font-size: 18px;
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 15px;
        }

        .empty-message:before {
            content: "📭";
            font-size: 48px;
        }

        /* ========== PIED DE PAGE ========== */
        .footer {
            background: #f8f9fa;
            padding: 20px 30px;
            text-align: center;
            color: #666;
            border-top: 1px solid #eee;
            font-size: 14px;
        }

        .footer strong {
            color: #4CAF50;
        }

        /* ========== LOADING SPINNER ========== */
        .loading {
            display: none;
            text-align: center;
            padding: 30px;
        }

        .spinner {
            border: 4px solid #f3f3f3;
            border-top: 4px solid #4CAF50;
            border-radius: 50%;
            width: 40px;
            height: 40px;
            animation: spin 1s linear infinite;
            margin: 0 auto;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }

        /* ========== RESPONSIVE ========== */
        @media (max-width: 768px) {
            .header {
                flex-direction: column;
                gap: 15px;
                text-align: center;
            }

            .user-info {
                width: 100%;
                justify-content: center;
            }

            .content {
                padding: 20px;
            }

            th, td {
                padding: 10px;
            }
        }

        /* ========== BADGES ET INDICATEURS ========== */
        .badge {
            background: #4CAF50;
            color: white;
            padding: 5px 10px;
            border-radius: 20px;
            font-size: 12px;
            margin-left: 10px;
        }

        .stats {
            display: flex;
            gap: 20px;
            margin-bottom: 20px;
            color: #666;
        }

        .stats-item {
            background: #f5f5f5;
            padding: 10px 20px;
            border-radius: 10px;
            display: flex;
            align-items: center;
            gap: 8px;
        }
    </style>
</head>
<body>

<div class="container">

    <!-- ========== EN-TÊTE AVEC UTILISATEUR CONNECTÉ ========== -->
    <div class="header">
        <h1>StockMaster Pro</h1>

        <div class="user-info">
            <span class="welcome">
                👤 Bienvenue,
                <span class="username">${sessionScope.user != null ? sessionScope.user : 'Invité'}</span>
            </span>
            <a href="${pageContext.request.contextPath}/connexion" class="logout-btn">
                Déconnexion
            </a>
        </div>
    </div>

    <!-- ========== CONTENU PRINCIPAL ========== -->
    <div class="content">

        <h2 class="section-title">Catalogue des produits</h2>

        <!-- Statistiques (optionnel) -->
        <div class="stats">
            <div class="stats-item">
                <span>📊 Total produits:</span>
                <strong>${listeProduits.size()}</strong>
            </div>
            <div class="stats-item">
                <span>💰 Prix moyen:</span>
                <strong>
                    <c:set var="total" value="0" />
                    <c:forEach var="p" items="${listeProduits}">
                        <c:set var="total" value="${total + p.prix}" />
                    </c:forEach>
                    <c:if test="${listeProduits.size() > 0}">
                        ${total / listeProduits.size()} €
                    </c:if>
                    <c:if test="${listeProduits.size() == 0}">
                        0 €
                    </c:if>
                </strong>
            </div>
        </div>

        <!-- TABLEAU DES PRODUITS -->
        <div class="table-container">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom du produit</th>
                    <th>Prix unitaire</th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${not empty listeProduits}">
                        <c:forEach var="p" items="${listeProduits}">
                            <tr>
                                <td>#${p.id}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${p.prix > 500}">
                                            💎 ${p.nom} <span class="badge">Premium</span>
                                        </c:when>
                                        <c:when test="${p.prix > 100}">
                                            ⭐ ${p.nom}
                                        </c:when>
                                        <c:otherwise>
                                            ${p.nom}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${p.prix}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="3">
                                <div class="empty-message">
                                    Aucun produit disponible dans le catalogue
                                    <small style="font-size: 14px; color: #999;">
                                        (Veuillez contacter l'administrateur)
                                    </small>
                                </div>
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>

        <!-- Informations complémentaires -->
        <div style="margin-top: 20px; padding: 15px; background: #f8f9fa; border-radius: 10px; border-left: 4px solid #4CAF50;">
            <p style="display: flex; align-items: center; gap: 10px; color: #666;">
                <span style="font-size: 20px;">ℹ️</span>
                <span>
                    <strong>Session:</strong>
                    ${sessionScope.user != null ? 'Active' : 'Inactive'} |
                    <strong>Dernier accès:</strong> ${sessionScope.lastAccess != null ? sessionScope.lastAccess : 'N/A'}
                </span>
            </p>
        </div>

    </div>

    <!-- ========== PIED DE PAGE ========== -->
    <div class="footer">
        <p>© 2026 StockMaster Pro - Système de Gestion de Stock</p>
        <p style="margin-top: 5px; font-size: 12px;">
            Développé avec 🛒 pour la gestion professionnelle
        </p>
    </div>

</div>

<!-- Script pour la déconnexion automatique (optionnel) -->
<script>
    // Afficher l'heure de dernière connexion
    document.addEventListener('DOMContentLoaded', function() {
        console.log('Catalogue chargé - Utilisateur: ${sessionScope.user}');
    });

    // Confirmation avant déconnexion
    const logoutBtn = document.querySelector('.logout-btn');
    if(logoutBtn) {
        logoutBtn.addEventListener('click', function(e) {
            if(!confirm('Voulez-vous vraiment vous déconnecter ?')) {
                e.preventDefault();
            }
        });
    }
</script>

</body>
</html>