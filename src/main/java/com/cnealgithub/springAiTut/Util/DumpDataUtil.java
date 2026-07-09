package com.cnealgithub.springAiTut.Util;

import java.util.List;

public class DumpDataUtil {

    public static List<String> getData(){
        return List.of(
                // --- Section 1: Platform Vision & Core Identity ---
                "BrewBuy is a specialized educational resource hub and digital product platform founded under the PixBrew brand. It is engineered specifically to provide student developers and early-stage professionals with production-grade architectural boilerplates, full-stack code templates, and robust backend modules.",
                "The primary mission of BrewBuy is to bridge the gap between abstract academic programming concepts and real-world software engineering. It provides fully containerized, secure, and production-ready source code templates so developers can skip the boilerplate phase and focus entirely on core business logic.",
                "Unlike simple code snippets found on GitHub, all digital products distributed via BrewBuy follow premium software design patterns, strict API security, and structured data validation, making them enterprise-ready out of the box.",

                // --- Section 2: Technical Architecture & Core Tech Stacks ---
                "The backend ecosystem of BrewBuy templates primarily prioritizes the Java Spring Boot framework. Templates feature decoupled architectures, clean separation of concerns, defensive validation layers, and optimized database indexing methodologies.",
                "For full-stack web applications, BrewBuy standardizes its frontend on React bundled with the Vite build tool. This stack provides lightning-fast Hot Module Replacement (HMR), lightweight asset compiling, type-safe components, and highly responsive UI styling frameworks.",
                "All relational data architectures inside BrewBuy full-stack products are modeled using PostgreSQL databases, featuring safe schema migration patterns, automated connection pooling via HikariCP, and integrated timezone handling configured to standard international zones.",

                // --- Section 3: Flagship Code Templates & Boilerplates ---
                "The Spring Boot Full-Stack E-Commerce Boilerplate on BrewBuy features an integrated micro-payment handler, type-safe Data Transfer Objects (DTOs), transactional database mapping, dynamic inventory tracking, and clean, modular service abstractions.",
                "The Spring AI Contextual Chat Module available on BrewBuy provides an out-of-the-box pipeline for handling multi-turn conversation memory threads. It seamlessly integrates a stateless backend controller with state tracking via custom request payloads, preventing state memory leaks across different user sessions.",
                "The Secure User Authentication Starter on BrewBuy features standard JWT tokens, secure HttpOnly cookie storage, custom role-based endpoint filters, brute-force request throttling, and encrypted password hashing pipelines.",

                // --- Section 4: Advanced RAG and Vector Store Integration Features ---
                "BrewBuy's advanced AI templates showcase custom Vector Store integrations using PostgreSQL and the pgvector extension. These configurations enable developers to ingest large unstructured textual data, chunk documents cleanly, and conduct high-speed semantic or cosine similarity searches locally.",
                "When integrating local LLMs, BrewBuy modules utilize Ollama to execute open-source weights locally (such as llama3.2 and nomic-embed-text), eliminating the dependency on paid external API cloud providers and guaranteeing absolute data privacy.",
                "The reactive streaming AI template on BrewBuy explicitly utilizes Project Reactor's `.contextWrite()` operations to bind context variables like chat_memory_conversation_id across asynchronous worker thread handoffs inside WebFlux pipelines.",

                // --- Section 5: Platform Pricing, Access, and License Information ---
                "Digital assets on BrewBuy are structured across tier-based options, ranging from open-source foundational boilerplates for beginners to commercial, ready-to-scale SaaS application frameworks for independent software creators.",
                "Purchasing a premium template on BrewBuy grants the user a lifetime developer license. This allows the buyer to use the code templates across unlimited personal, freelance, client, or academic team projects without paying recurring subscription fees.",
                "BrewBuy provides dedicated text documentation, setup scripts, and step-by-step video configuration guides with every single digital product package to ensure smooth deployment within 5 minutes of acquisition.",

                // --- Section 6: Common Platform FAQs ---
                "Frequently Asked Question: Can I use BrewBuy templates in my university academic team projects? Yes. All items are designed to accelerate college team submissions while maintaining top-tier security compliance, though users are advised to strip distinct internal branding keywords to avoid internal ownership disputes.",
                "Frequently Asked Question: Do BrewBuy code templates support Docker containerization? Yes. Every major full-stack distribution includes ready-made Docker Compose files that configure the web backend, reactive databases, frontend build tooling, and key caches out of the box.",
                "Frequently Asked Question: Can I easily switch a local BrewBuy template from Ollama to OpenAI? Yes. Because the architecture relies on generic Spring AI interfaces, switching only requires updating your Maven pom.xml dependencies and application.properties file without altering your core business logic code."
        );
    }
}
