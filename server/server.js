const express = require("express");
const app = express();
const port = process.env.PORT || 3000;

app.use(express.json());

// Allow access from emulator/other devices (helpful for development)
app.use((req, res, next) => {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
  res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
  if (req.method === "OPTIONS") return res.sendStatus(204);
  next();
});

// In-memory store (RAM)
let dataStore = [];

// Root endpoint (avoid "Cannot GET /")
app.get("/", (req, res) => {
  res.send("API is running ✅. Try GET /api/data or POST /api/data");
});

// GET data
app.get("/api/data", (req, res) => {
  res.json({
    message: "Hello from API",
    data: dataStore,
  });
});

// POST data
app.post("/api/data", (req, res) => {
  const name = req.body?.name;

  if (typeof name !== "string" || name.trim().length === 0) {
    return res.status(400).json({ error: "Name is required (string)" });
  }

  dataStore.push(name.trim());
  res.json({ message: `Data received: ${name.trim()}` });
});

// OPTIONAL: clear data for testing
app.delete("/api/data", (req, res) => {
  dataStore = [];
  res.json({ message: "Data cleared" });
});

app.listen(port, "0.0.0.0", () => {
  console.log(`Server running at http://localhost:${port}`);
});
